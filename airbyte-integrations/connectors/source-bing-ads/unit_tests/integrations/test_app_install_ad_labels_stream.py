# Copyright (c) 2023 Airbyte, Inc., all rights reserved.

from freezegun import freeze_time
from test_bulk_stream import TestBulkStream

from airbyte_cdk.models import SyncMode


class TestAppInstallAdLabelsStream(TestBulkStream):
    stream_name = "app_install_ad_labels"
    account_id = "180535609"
    cursor_field = "Modified Time"
    download_entity = "AppInstallAdLabels"

    def test_return_records_from_given_csv_file(self):
        self.mock_apis(file=self.stream_name)
        output = self.read_stream(self.stream_name, SyncMode.full_refresh, self._config, "app_install_ad_labels")
        assert len(output.records) == 1

    def test_return_zero_record_from_empty_csv(self):
        self.mock_apis(file="app_install_ad_labels_empty")
        output = self.read_stream(self.stream_name, SyncMode.full_refresh, self._config, "app_install_ad_labels_empty")
        assert len(output.records) == 0
        no_records_message = self.create_log_message(f"Read 0 records from {self.stream_name} stream")
        assert no_records_message in output.logs

    def test_transform_records(self):
        self.mock_apis(file=self.stream_name)
        output = self.read_stream(self.stream_name, SyncMode.full_refresh, self._config, "app_install_ad_labels")
        assert output.records
        for record in output.records:
            assert "Account Id" in record.record.data.keys()
            assert isinstance(record.record.data["Account Id"], int)

    @freeze_time("2024-02-26")
    def test_incremental_read_cursor_value_matches_value_from_most_recent_record(self):
        self.mock_apis(file="app_install_ad_labels_with_cursor_value")
        output = self.read_stream(self.stream_name, SyncMode.incremental, self._config, "app_install_ad_labels_with_cursor_value")
        assert len(output.records) == 4
        assert output.most_recent_state.stream_state.states[0]["cursor"] == {self.cursor_field: "2024-01-04T12:12:12.028+0000"}

    @freeze_time("2024-02-26")  # mock current time as stream data available for 30 days only
    def test_incremental_read_with_state(self):
        self.mock_apis(file="app_install_ad_labels_with_state", read_with_state=True)
        state = self._state("app_install_ad_labels_state", self.stream_name)
        output = self.read_stream(self.stream_name, SyncMode.incremental, self._config, "app_install_ad_labels_with_state", state)
        assert output.most_recent_state.stream_state.states[0]["cursor"] == {self.cursor_field: "2024-01-29T12:55:12.028+0000"}
