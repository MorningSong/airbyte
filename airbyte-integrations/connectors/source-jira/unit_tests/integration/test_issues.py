# Copyright (c) 2024 Airbyte, Inc., all rights reserved.
import json
import os
from datetime import datetime, timezone
from typing import Any, Dict
from unittest import TestCase

import freezegun
from conftest import _YAML_FILE_PATH

from airbyte_cdk.models import ConfiguredAirbyteCatalog, SyncMode
from airbyte_cdk.sources.declarative.yaml_declarative_source import YamlDeclarativeSource
from airbyte_cdk.test.catalog_builder import CatalogBuilder
from airbyte_cdk.test.entrypoint_wrapper import read
from airbyte_cdk.test.mock_http import HttpMocker, HttpRequest
from airbyte_cdk.test.mock_http.response_builder import (
    FieldPath,
    HttpResponseBuilder,
    RecordBuilder,
    create_record_builder,
    create_response_builder,
)
from airbyte_cdk.test.state_builder import StateBuilder
from integration.config import ConfigBuilder


_STREAM_NAME = "issues"
_API_TOKEN = "api_token"
_DOMAIN = "airbyteio.atlassian.net"
_NOW = datetime(2024, 1, 1, tzinfo=timezone.utc)


def _create_config() -> ConfigBuilder:
    return ConfigBuilder().with_api_token(_API_TOKEN).with_domain(_DOMAIN)


def _create_catalog(sync_mode: SyncMode = SyncMode.full_refresh) -> ConfiguredAirbyteCatalog:
    return CatalogBuilder().with_stream(name="issues", sync_mode=sync_mode).build()


def _response_template() -> Dict[str, Any]:
    with open(os.path.join(os.path.dirname(__file__), "..", "responses", "issues.json")) as response_file_handler:
        return json.load(response_file_handler)


def _create_response() -> HttpResponseBuilder:
    return create_response_builder(
        response_template=_response_template(),
        records_path=FieldPath("issues"),
    )


def _create_record() -> RecordBuilder:
    return create_record_builder(
        _response_template(), FieldPath("issues"), record_id_path=FieldPath("id"), record_cursor_path=FieldPath("updated")
    )


@freezegun.freeze_time(_NOW.isoformat())
class IssuesTest(TestCase):
    @HttpMocker()
    def test_given_timezone_in_state_when_read_consider_timezone(self, http_mocker: HttpMocker) -> None:
        config = _create_config().build()
        datetime_with_timezone = "2023-11-01T00:00:00.000-0800"
        timestamp_with_timezone = 1698825600000
        state = (
            StateBuilder()
            .with_stream_state(
                "issues",
                {
                    "use_global_cursor": False,
                    "state": {"updated": datetime_with_timezone},
                    "lookback_window": 2,
                    "states": [{"partition": {}, "cursor": {"updated": datetime_with_timezone}}],
                },
            )
            .build()
        )
        http_mocker.get(
            HttpRequest(
                f"https://{_DOMAIN}/rest/api/3/search/jql",
                {
                    "fields": "*all",
                    "jql": f"updated >= {timestamp_with_timezone} ORDER BY updated asc",
                    "expand": "renderedFields,transitions,changelog",
                    "maxResults": "50",
                },
            ),
            _create_response().with_record(_create_record()).with_record(_create_record()).build(),
        )

        source = YamlDeclarativeSource(config=config, catalog=_create_catalog(), state=state, path_to_yaml=str(_YAML_FILE_PATH))
        actual_messages = read(source, config=config, catalog=_create_catalog(), state=state)

        assert len(actual_messages.records) == 2
