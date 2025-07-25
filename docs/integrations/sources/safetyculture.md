# SafetyCulture

This is the guide for the Safetyculture source connector which ingests data from the Safetyculture API.

## Prerequisites

This source uses the Authorization Bearer Token for handling requests. In order to obtain the credientials, you must first create a Safetyculture account.
The API usage is only availabe for paid plans https://www.safetyculture.com/

Once you have created your account, you can log in to your account.
You can create an API token under Account Settings -> Integrations -> Manage MY API Tokens
You can find more about their API here https://developer.safetyculture.com/reference/introduction

## Set up the Adjust source connector

1. Click **Sources** and then click **+ New source**.
2. On the Set up the source page, select **Safetyculture** from the Source type dropdown.
3. Enter a name for your new source.
4. For **API Token**, enter your API token obtained in the previous step.
5. Click **Set up source**.

## Supported sync modes

The source connector supports the following [sync modes](https://docs.airbyte.com/cloud/core-concepts#connection-sync-modes):

- Full Refresh

## Configuration

| Input | Type | Description | Default Value |
|-------|------|-------------|---------------|
| `api_key` | `string` | API Key.  |  |

## Streams
| Stream Name | Primary Key | Pagination | Supports Full Sync | Supports Incremental |
|-------------|-------------|------------|---------------------|----------------------|
| feed_users | id | DefaultPaginator | ✅ |  ❌  |
| groups | id | No pagination | ✅ |  ❌  |
| connections | id | No pagination | ✅ |  ❌  |
| heads_up | id | DefaultPaginator | ✅ |  ❌  |
| assets | id | DefaultPaginator | ✅ |  ❌  |
| folders | id | DefaultPaginator | ✅ |  ❌  |
| global_response_sets | responseset_id | No pagination | ✅ |  ❌  |
| schedule_items | id | DefaultPaginator | ✅ |  ❌  |
| actions | unique_id | DefaultPaginator | ✅ |  ❌  |
| templates | template_id | No pagination | ✅ |  ❌  |
| feed_templates | id | DefaultPaginator | ✅ |  ❌  |
| issues | unique_id | DefaultPaginator | ✅ |  ❌  |

## Changelog

<details>
  <summary>Expand to review</summary>

| Version          | Date              | Pull Request | Subject        |
|------------------|-------------------|--------------|----------------|
| 0.0.33 | 2025-07-19 | [63630](https://github.com/airbytehq/airbyte/pull/63630) | Update dependencies |
| 0.0.32 | 2025-07-12 | [63038](https://github.com/airbytehq/airbyte/pull/63038) | Update dependencies |
| 0.0.31 | 2025-07-05 | [62746](https://github.com/airbytehq/airbyte/pull/62746) | Update dependencies |
| 0.0.30 | 2025-06-28 | [62240](https://github.com/airbytehq/airbyte/pull/62240) | Update dependencies |
| 0.0.29 | 2025-06-21 | [61306](https://github.com/airbytehq/airbyte/pull/61306) | Update dependencies |
| 0.0.28 | 2025-05-25 | [60518](https://github.com/airbytehq/airbyte/pull/60518) | Update dependencies |
| 0.0.27 | 2025-05-10 | [60058](https://github.com/airbytehq/airbyte/pull/60058) | Update dependencies |
| 0.0.26 | 2025-05-04 | [59623](https://github.com/airbytehq/airbyte/pull/59623) | Update dependencies |
| 0.0.25 | 2025-04-27 | [58967](https://github.com/airbytehq/airbyte/pull/58967) | Update dependencies |
| 0.0.24 | 2025-04-19 | [58391](https://github.com/airbytehq/airbyte/pull/58391) | Update dependencies |
| 0.0.23 | 2025-04-12 | [57996](https://github.com/airbytehq/airbyte/pull/57996) | Update dependencies |
| 0.0.22 | 2025-04-05 | [57428](https://github.com/airbytehq/airbyte/pull/57428) | Update dependencies |
| 0.0.21 | 2025-03-29 | [56731](https://github.com/airbytehq/airbyte/pull/56731) | Update dependencies |
| 0.0.20 | 2025-03-22 | [56169](https://github.com/airbytehq/airbyte/pull/56169) | Update dependencies |
| 0.0.19 | 2025-03-08 | [55554](https://github.com/airbytehq/airbyte/pull/55554) | Update dependencies |
| 0.0.18 | 2025-03-01 | [55007](https://github.com/airbytehq/airbyte/pull/55007) | Update dependencies |
| 0.0.17 | 2025-02-23 | [54593](https://github.com/airbytehq/airbyte/pull/54593) | Update dependencies |
| 0.0.16 | 2025-02-15 | [54007](https://github.com/airbytehq/airbyte/pull/54007) | Update dependencies |
| 0.0.15 | 2025-02-08 | [53511](https://github.com/airbytehq/airbyte/pull/53511) | Update dependencies |
| 0.0.14 | 2025-02-01 | [52988](https://github.com/airbytehq/airbyte/pull/52988) | Update dependencies |
| 0.0.13 | 2025-01-25 | [52492](https://github.com/airbytehq/airbyte/pull/52492) | Update dependencies |
| 0.0.12 | 2025-01-18 | [51875](https://github.com/airbytehq/airbyte/pull/51875) | Update dependencies |
| 0.0.11 | 2025-01-11 | [51362](https://github.com/airbytehq/airbyte/pull/51362) | Update dependencies |
| 0.0.10 | 2024-12-28 | [50674](https://github.com/airbytehq/airbyte/pull/50674) | Update dependencies |
| 0.0.9 | 2024-12-21 | [50297](https://github.com/airbytehq/airbyte/pull/50297) | Update dependencies |
| 0.0.8 | 2024-12-14 | [49669](https://github.com/airbytehq/airbyte/pull/49669) | Update dependencies |
| 0.0.7 | 2024-12-12 | [49358](https://github.com/airbytehq/airbyte/pull/49358) | Update dependencies |
| 0.0.6 | 2024-12-11 | [49086](https://github.com/airbytehq/airbyte/pull/49086) | Starting with this version, the Docker image is now rootless. Please note that this and future versions will not be compatible with Airbyte versions earlier than 0.64 |
| 0.0.5 | 2024-11-05 | [48362](https://github.com/airbytehq/airbyte/pull/48362) | Revert to source-declarative-manifest v5.17.0 |
| 0.0.4 | 2024-11-05 | [48325](https://github.com/airbytehq/airbyte/pull/48325) | Update dependencies |
| 0.0.3 | 2024-10-29 | [47839](https://github.com/airbytehq/airbyte/pull/47839) | Update dependencies |
| 0.0.2 | 2024-10-28 | [47586](https://github.com/airbytehq/airbyte/pull/47586) | Update dependencies |
| 0.0.1 | 2024-10-04 | | Initial release by [@aazam-gh](https://github.com/aazam-gh) via Connector Builder |

</details>
