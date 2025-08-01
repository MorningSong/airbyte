# Open Data DC

Open Data DC source connector which ingests data from the MAR 2 API.

## Prerequisites

Create a developer MAR account at https://developers.data.dc.gov/ to obtain your API key.

The MAR 2 API allows users to search for addresses, place names, blocks and intersections within the DC boundary.
In order to use this search, input the string in the `location` field.

MARID is the Master Address Repository ID associated with all addresses within the DC boundary. 

## Set up the Adjust source connector

1. Click **Sources** and then click **+ New source**.
2. On the Set up the source page, select **Open Data DC** from the Source type dropdown.
3. Enter a name for your new source.
4. For **API Key**, enter your API key obtained in the previous step.
5. For **location**, enter any string to search for a location as explained in the previous step.
6. For **marid**, enter your MARID as explained in the previous step.
7. Click **Set up source**.

## Configuration

| Input | Type | Description | Default Value |
|-------|------|-------------|---------------|
| `api_key` | `string` | API Key.  |  |
| `location` | `string` | location. address or place or block |  |
| `marid` | `string` | marid. A unique identifier (Master Address Repository). |  |

## Streams
| Stream Name | Primary Key | Pagination | Supports Full Sync | Supports Incremental |
|-------------|-------------|------------|---------------------|----------------------|
| locations |  | No pagination | ✅ |  ❌  |
| units | UnitNum | No pagination | ✅ |  ❌  |
| ssls | SSL | No pagination | ✅ |  ❌  |

## Changelog

<details>
  <summary>Expand to review</summary>

| Version          | Date              | Pull Request | Subject        |
|------------------|-------------------|--------------|----------------|
| 0.0.30 | 2025-07-26 | [63880](https://github.com/airbytehq/airbyte/pull/63880) | Update dependencies |
| 0.0.29 | 2025-07-19 | [63386](https://github.com/airbytehq/airbyte/pull/63386) | Update dependencies |
| 0.0.28 | 2025-07-12 | [63190](https://github.com/airbytehq/airbyte/pull/63190) | Update dependencies |
| 0.0.27 | 2025-07-05 | [62589](https://github.com/airbytehq/airbyte/pull/62589) | Update dependencies |
| 0.0.26 | 2025-06-28 | [62386](https://github.com/airbytehq/airbyte/pull/62386) | Update dependencies |
| 0.0.25 | 2025-06-21 | [61870](https://github.com/airbytehq/airbyte/pull/61870) | Update dependencies |
| 0.0.24 | 2025-06-14 | [61072](https://github.com/airbytehq/airbyte/pull/61072) | Update dependencies |
| 0.0.23 | 2025-05-24 | [60517](https://github.com/airbytehq/airbyte/pull/60517) | Update dependencies |
| 0.0.22 | 2025-05-10 | [60068](https://github.com/airbytehq/airbyte/pull/60068) | Update dependencies |
| 0.0.21 | 2025-05-03 | [59463](https://github.com/airbytehq/airbyte/pull/59463) | Update dependencies |
| 0.0.20 | 2025-04-27 | [59096](https://github.com/airbytehq/airbyte/pull/59096) | Update dependencies |
| 0.0.19 | 2025-04-19 | [58464](https://github.com/airbytehq/airbyte/pull/58464) | Update dependencies |
| 0.0.18 | 2025-04-12 | [57307](https://github.com/airbytehq/airbyte/pull/57307) | Update dependencies |
| 0.0.17 | 2025-03-29 | [56178](https://github.com/airbytehq/airbyte/pull/56178) | Update dependencies |
| 0.0.16 | 2025-03-08 | [55544](https://github.com/airbytehq/airbyte/pull/55544) | Update dependencies |
| 0.0.15 | 2025-03-01 | [55032](https://github.com/airbytehq/airbyte/pull/55032) | Update dependencies |
| 0.0.14 | 2025-02-23 | [54614](https://github.com/airbytehq/airbyte/pull/54614) | Update dependencies |
| 0.0.13 | 2025-02-15 | [53992](https://github.com/airbytehq/airbyte/pull/53992) | Update dependencies |
| 0.0.12 | 2025-02-08 | [53454](https://github.com/airbytehq/airbyte/pull/53454) | Update dependencies |
| 0.0.11 | 2025-02-01 | [52977](https://github.com/airbytehq/airbyte/pull/52977) | Update dependencies |
| 0.0.10 | 2025-01-25 | [52498](https://github.com/airbytehq/airbyte/pull/52498) | Update dependencies |
| 0.0.9 | 2025-01-18 | [51893](https://github.com/airbytehq/airbyte/pull/51893) | Update dependencies |
| 0.0.8 | 2025-01-11 | [51349](https://github.com/airbytehq/airbyte/pull/51349) | Update dependencies |
| 0.0.7 | 2024-12-28 | [50239](https://github.com/airbytehq/airbyte/pull/50239) | Update dependencies |
| 0.0.6 | 2024-12-14 | [49703](https://github.com/airbytehq/airbyte/pull/49703) | Update dependencies |
| 0.0.5 | 2024-12-12 | [49364](https://github.com/airbytehq/airbyte/pull/49364) | Update dependencies |
| 0.0.4 | 2024-12-11 | [49096](https://github.com/airbytehq/airbyte/pull/49096) | Starting with this version, the Docker image is now rootless. Please note that this and future versions will not be compatible with Airbyte versions earlier than 0.64 |
| 0.0.3 | 2024-10-29 | [47912](https://github.com/airbytehq/airbyte/pull/47912) | Update dependencies |
| 0.0.2 | 2024-10-28 | [47594](https://github.com/airbytehq/airbyte/pull/47594) | Update dependencies |
| 0.0.1 | 2024-10-06 | | Initial release by [@aazam-gh](https://github.com/aazam-gh) via Connector Builder |

</details>
