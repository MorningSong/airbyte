# OneSignal

This page contains the setup guide and reference information for the OneSignal source connector.

## Prerequisites

- [User Auth Key](https://documentation.onesignal.com/docs/accounts-and-keys#user-auth-key)
- Applications [credentials](https://documentation.onesignal.com/docs/accounts-and-keys) \(App Id & REST API Key\)

## Setup guide

### Step 1: Set up OneSignal

### Step 2: Set up the OneSignal connector in Airbyte

#### For Airbyte Cloud:

1. [Log into your Airbyte Cloud](https://cloud.airbyte.io/workspaces) account.
2. Click **Sources** and then click **+ New source**.
3. On the Set up the source page, select **OneSignal** from the **Source type** dropdown.
4. Enter a name for the OneSignal connector.
5. Enter [User Auth Key](https://documentation.onesignal.com/docs/accounts-and-keys#user-auth-key)
6. Enter Applications credentials (repeat for every application):
   1. Enter App Name (for internal purposes only)
   2. Enter [App ID](https://documentation.onesignal.com/docs/accounts-and-keys#app-id)
   3. Enter [REST API Key](https://documentation.onesignal.com/docs/accounts-and-keys#rest-api-key)
7. Enter the Start Date in format `YYYY-MM-DDTHH:mm:ssZ`
8. Enter Outcome names as comma separated values, e.g. `os__session_duration.count,os__click.count,` see the [API docs](https://documentation.onesignal.com/reference/view-outcomes) for more details.

#### For Airbyte Open Source:

1. Navigate to the Airbyte Open Source dashboard.
2. Click **Sources** and then click **+ New source**.
3. On the Set up the source page, select **OneSignal** from the Source type dropdown.
4. Enter the name for the OneSignal connector.
5. Enter [User Auth Key](https://documentation.onesignal.com/docs/accounts-and-keys#user-auth-key)
6. Enter Applications credentials (repeat for every application):
   1. Enter App Name (for internal purposes only)
   2. Enter [App ID](https://documentation.onesignal.com/docs/accounts-and-keys#app-id)
   3. Enter [REST API Key](https://documentation.onesignal.com/docs/accounts-and-keys#rest-api-key)
7. Enter the Start Date in format `YYYY-MM-DDTHH:mm:ssZ`
8. Enter Outcome names as comma separated values, e.g. `os__session_duration.count,os__click.count,` see the [API docs](https://documentation.onesignal.com/reference/view-outcomes) for more details.

## Supported sync modes

The OneSignal source connector supports the following [sync modes](https://docs.airbyte.com/cloud/core-concepts#connection-sync-modes):

- [Full Refresh - Overwrite](https://docs.airbyte.com/understanding-airbyte/connections/full-refresh-overwrite/)
- [Full Refresh - Append](https://docs.airbyte.com/understanding-airbyte/connections/full-refresh-append)
- [Incremental - Append](https://docs.airbyte.com/understanding-airbyte/connections/incremental-append)
- [Incremental - Append + Deduped](https://docs.airbyte.com/understanding-airbyte/connections/incremental-append-deduped)

## Supported Streams

- [Apps](https://documentation.onesignal.com/reference/view-apps-apps)
- [Devices](https://documentation.onesignal.com/reference/view-devices) \(Incremental\)
- [Notifications](https://documentation.onesignal.com/reference/view-notification) \(Incremental\)
- [Outcomes](https://documentation.onesignal.com/reference/view-outcomes)

## Performance considerations

The connector is restricted by normal OneSignal [rate limits](https://documentation.onesignal.com/docs/rate-limits).

## Data type mapping

| Integration Type | Airbyte Type | Notes |
| :--------------- | :----------- | :---- |
| `string`         | `string`     |       |
| `integer`        | `integer`    |       |
| `number`         | `number`     |       |
| `array`          | `array`      |       |
| `object`         | `object`     |       |

## Changelog

<details>
  <summary>Expand to review</summary>

| Version | Date       | Pull Request                                             | Subject                                      |
| :------ | :--------- | :------------------------------------------------------- | :------------------------------------------- |
| 1.2.30 | 2025-07-26 | [63870](https://github.com/airbytehq/airbyte/pull/63870) | Update dependencies |
| 1.2.29 | 2025-07-19 | [63415](https://github.com/airbytehq/airbyte/pull/63415) | Update dependencies |
| 1.2.28 | 2025-07-12 | [63263](https://github.com/airbytehq/airbyte/pull/63263) | Update dependencies |
| 1.2.27 | 2025-07-05 | [62555](https://github.com/airbytehq/airbyte/pull/62555) | Update dependencies |
| 1.2.26 | 2025-06-28 | [62359](https://github.com/airbytehq/airbyte/pull/62359) | Update dependencies |
| 1.2.25 | 2025-06-21 | [61877](https://github.com/airbytehq/airbyte/pull/61877) | Update dependencies |
| 1.2.24 | 2025-06-14 | [61032](https://github.com/airbytehq/airbyte/pull/61032) | Update dependencies |
| 1.2.23 | 2025-05-24 | [60514](https://github.com/airbytehq/airbyte/pull/60514) | Update dependencies |
| 1.2.22 | 2025-05-10 | [60092](https://github.com/airbytehq/airbyte/pull/60092) | Update dependencies |
| 1.2.21 | 2025-05-04 | [59504](https://github.com/airbytehq/airbyte/pull/59504) | Update dependencies |
| 1.2.20 | 2025-04-27 | [59054](https://github.com/airbytehq/airbyte/pull/59054) | Update dependencies |
| 1.2.19 | 2025-04-19 | [58518](https://github.com/airbytehq/airbyte/pull/58518) | Update dependencies |
| 1.2.18 | 2025-04-12 | [57904](https://github.com/airbytehq/airbyte/pull/57904) | Update dependencies |
| 1.2.17 | 2025-04-05 | [56773](https://github.com/airbytehq/airbyte/pull/56773) | Update dependencies |
| 1.2.16 | 2025-03-22 | [56237](https://github.com/airbytehq/airbyte/pull/56237) | Update dependencies |
| 1.2.15 | 2025-03-08 | [55545](https://github.com/airbytehq/airbyte/pull/55545) | Update dependencies |
| 1.2.14 | 2025-03-01 | [55040](https://github.com/airbytehq/airbyte/pull/55040) | Update dependencies |
| 1.2.13 | 2025-02-23 | [54587](https://github.com/airbytehq/airbyte/pull/54587) | Update dependencies |
| 1.2.12 | 2025-02-15 | [53964](https://github.com/airbytehq/airbyte/pull/53964) | Update dependencies |
| 1.2.11 | 2025-02-08 | [53461](https://github.com/airbytehq/airbyte/pull/53461) | Update dependencies |
| 1.2.10 | 2025-02-01 | [53007](https://github.com/airbytehq/airbyte/pull/53007) | Update dependencies |
| 1.2.9 | 2025-01-25 | [52539](https://github.com/airbytehq/airbyte/pull/52539) | Update dependencies |
| 1.2.8 | 2025-01-18 | [51868](https://github.com/airbytehq/airbyte/pull/51868) | Update dependencies |
| 1.2.7 | 2025-01-11 | [51321](https://github.com/airbytehq/airbyte/pull/51321) | Update dependencies |
| 1.2.6 | 2024-12-28 | [50719](https://github.com/airbytehq/airbyte/pull/50719) | Update dependencies |
| 1.2.5 | 2024-12-21 | [50243](https://github.com/airbytehq/airbyte/pull/50243) | Update dependencies |
| 1.2.4 | 2024-12-14 | [49706](https://github.com/airbytehq/airbyte/pull/49706) | Update dependencies |
| 1.2.3 | 2024-12-12 | [49349](https://github.com/airbytehq/airbyte/pull/49349) | Update dependencies |
| 1.2.2 | 2024-12-11 | [49106](https://github.com/airbytehq/airbyte/pull/49106) | Starting with this version, the Docker image is now rootless. Please note that this and future versions will not be compatible with Airbyte versions earlier than 0.64 |
| 1.2.1 | 2024-10-29 | [47667](https://github.com/airbytehq/airbyte/pull/47667) | Update dependencies |
| 1.2.0 | 2024-10-05 | [46372](https://github.com/airbytehq/airbyte/pull/46372) | Converting to manifest-only format |
| 1.1.14 | 2024-09-28 | [46184](https://github.com/airbytehq/airbyte/pull/46184) | Update dependencies |
| 1.1.13 | 2024-09-21 | [45788](https://github.com/airbytehq/airbyte/pull/45788) | Update dependencies |
| 1.1.12 | 2024-09-14 | [45528](https://github.com/airbytehq/airbyte/pull/45528) | Update dependencies |
| 1.1.11 | 2024-09-07 | [45228](https://github.com/airbytehq/airbyte/pull/45228) | Update dependencies |
| 1.1.10 | 2024-08-31 | [45046](https://github.com/airbytehq/airbyte/pull/45046) | Update dependencies |
| 1.1.9 | 2024-08-24 | [44673](https://github.com/airbytehq/airbyte/pull/44673) | Update dependencies |
| 1.1.8 | 2024-08-17 | [44333](https://github.com/airbytehq/airbyte/pull/44333) | Update dependencies |
| 1.1.7 | 2024-08-12 | [43767](https://github.com/airbytehq/airbyte/pull/43767) | Update dependencies |
| 1.1.6 | 2024-08-10 | [43468](https://github.com/airbytehq/airbyte/pull/43468) | Update dependencies |
| 1.1.5 | 2024-08-03 | [42742](https://github.com/airbytehq/airbyte/pull/42742) | Update dependencies |
| 1.1.4 | 2024-07-20 | [42293](https://github.com/airbytehq/airbyte/pull/42293) | Update dependencies |
| 1.1.3 | 2024-07-13 | [41785](https://github.com/airbytehq/airbyte/pull/41785) | Update dependencies |
| 1.1.2 | 2024-07-10 | [41317](https://github.com/airbytehq/airbyte/pull/41317) | Update dependencies |
| 1.1.1 | 2024-07-09 | [41033](https://github.com/airbytehq/airbyte/pull/41033) | Migrate to poetry base |
| 1.1.0 | 2023-08-31 | [28941](https://github.com/airbytehq/airbyte/pull/28941) | Migrate connector to low-code |
| 1.0.1 | 2023-03-14 | [24076](https://github.com/airbytehq/airbyte/pull/24076) | Fix schema and add additionalProperties true |
| 1.0.0 | 2023-03-14 | [24076](https://github.com/airbytehq/airbyte/pull/24076) | Update connectors spec; fix incremental sync |
| 0.1.2 | 2021-12-07 | [8582](https://github.com/airbytehq/airbyte/pull/8582) | Update connector fields title/description |
| 0.1.1 | 2021-11-10 | [7617](https://github.com/airbytehq/airbyte/pull/7617) | Fix get_update state |
| 0.1.0 | 2021-10-13 | [6998](https://github.com/airbytehq/airbyte/pull/6998) | Initial Release |

</details>
