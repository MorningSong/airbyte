# Pendo

Pendo is powerful product analytics tool. The source connector here allows you to fetch data from the v1 API.

Currently, the aggregation endpoint is not supported. Please [create an issue](https://github.com/airbytehq/airbyte/issues/new/choose) if you want more streams supported here.

## Prerequisites

- Created Pendo and enable the integration feature
- Generate [an API token](https://app.pendo.io/admin/integrationkeys)

## Airbyte Open Source

- Api Token

## Airbyte Cloud

- Api Token

## Setup guide

### Create an integration Key In Pendo

1. Login to the Pendo Application as an Admin user.
2. Visit the **Integrations** section in the Pendo App, and then click on **Integration Keys**.
3. Click on the **Add Integration Key** button at the top right-hand side of the screen.
4. Give your new key an appropriate description.
5. If a read-write key is needed, tick the box marked **Allow Write Access**.
6. Click on **Create** to finish.

### For Airbyte Cloud:

1. [Log into your Airbyte Cloud](https://cloud.airbyte.com/workspaces) account.
2. In the left navigation bar, click **Sources**. In the top-right corner, click **+ new source**.
3. On the source setup page, select **Pendo** from the Source type dropdown and enter a name for this connector.
4. Add **API Key** from the last step
5. Click `Set up source`.

### For Airbyte Open Source:

1. Go to the Airbyte you deployed, for example, http://localhost:8000
2. In the left navigation bar, click **Sources**. In the top-right corner, click **+ new source**.
3. On the source setup page, select **Pendo** from the Source type dropdown and enter a name for this connector.
4. Add **API Key** from the last step
5. Click `Set up source`.

## Supported sync modes

The Pendo source connector supports the following [sync modes](https://docs.airbyte.com/cloud/core-concepts#connection-sync-modes):

- Full Refresh.

## Supported Streams

- [Page](https://engageapi.pendo.io/#a53463f9-bdd3-443e-b22f-b6ea6c7376fb)
- [Feature](https://engageapi.pendo.io/#75c6b443-eb07-4a0c-9e27-6c12ad3dbbc4)
- [Report](https://engageapi.pendo.io/#2ac0699a-b653-4082-be11-563e5c0c9410)
- [Guide](https://engageapi.pendo.io/#4f1e3ca1-fc41-4469-bf4b-da90ee8caf3d)

## Changelog

<details>
  <summary>Expand to review</summary>

| Version | Date       | Pull Request                                             | Subject                                                                         |
| :------ | :--------- | :------------------------------------------------------- | :------------------------------------------------------------------------------ |
| 0.2.28 | 2025-07-26 | [63924](https://github.com/airbytehq/airbyte/pull/63924) | Update dependencies |
| 0.2.27 | 2025-07-19 | [63434](https://github.com/airbytehq/airbyte/pull/63434) | Update dependencies |
| 0.2.26 | 2025-07-12 | [63264](https://github.com/airbytehq/airbyte/pull/63264) | Update dependencies |
| 0.2.25 | 2025-07-05 | [62594](https://github.com/airbytehq/airbyte/pull/62594) | Update dependencies |
| 0.2.24 | 2025-06-28 | [62298](https://github.com/airbytehq/airbyte/pull/62298) | Update dependencies |
| 0.2.23 | 2025-06-21 | [60580](https://github.com/airbytehq/airbyte/pull/60580) | Update dependencies |
| 0.2.22 | 2025-05-11 | [60204](https://github.com/airbytehq/airbyte/pull/60204) | Update dependencies |
| 0.2.21 | 2025-05-04 | [59515](https://github.com/airbytehq/airbyte/pull/59515) | Update dependencies |
| 0.2.20 | 2025-04-27 | [59104](https://github.com/airbytehq/airbyte/pull/59104) | Update dependencies |
| 0.2.19 | 2025-04-19 | [58485](https://github.com/airbytehq/airbyte/pull/58485) | Update dependencies |
| 0.2.18 | 2025-04-12 | [57883](https://github.com/airbytehq/airbyte/pull/57883) | Update dependencies |
| 0.2.17 | 2025-04-05 | [57367](https://github.com/airbytehq/airbyte/pull/57367) | Update dependencies |
| 0.2.16 | 2025-03-29 | [56742](https://github.com/airbytehq/airbyte/pull/56742) | Update dependencies |
| 0.2.15 | 2025-03-22 | [56161](https://github.com/airbytehq/airbyte/pull/56161) | Update dependencies |
| 0.2.14 | 2025-03-08 | [55559](https://github.com/airbytehq/airbyte/pull/55559) | Update dependencies |
| 0.2.13 | 2025-03-01 | [55031](https://github.com/airbytehq/airbyte/pull/55031) | Update dependencies |
| 0.2.12 | 2025-02-23 | [54580](https://github.com/airbytehq/airbyte/pull/54580) | Update dependencies |
| 0.2.11 | 2025-02-15 | [54003](https://github.com/airbytehq/airbyte/pull/54003) | Update dependencies |
| 0.2.10 | 2025-02-08 | [53456](https://github.com/airbytehq/airbyte/pull/53456) | Update dependencies |
| 0.2.9 | 2025-02-01 | [52970](https://github.com/airbytehq/airbyte/pull/52970) | Update dependencies |
| 0.2.8 | 2025-01-25 | [52524](https://github.com/airbytehq/airbyte/pull/52524) | Update dependencies |
| 0.2.7 | 2025-01-18 | [51901](https://github.com/airbytehq/airbyte/pull/51901) | Update dependencies |
| 0.2.6 | 2025-01-11 | [51299](https://github.com/airbytehq/airbyte/pull/51299) | Update dependencies |
| 0.2.5 | 2025-01-04 | [50244](https://github.com/airbytehq/airbyte/pull/50244) | Update dependencies |
| 0.2.4 | 2024-12-14 | [48313](https://github.com/airbytehq/airbyte/pull/48313) | Update dependencies |
| 0.2.3 | 2024-10-29 | [47847](https://github.com/airbytehq/airbyte/pull/47847) | Update dependencies |
| 0.2.2 | 2024-10-28 | [47563](https://github.com/airbytehq/airbyte/pull/47563) | Update dependencies |
| 0.2.1 | 2024-08-16 | [44196](https://github.com/airbytehq/airbyte/pull/44196) | Bump source-declarative-manifest version |
| 0.2.0 | 2024-08-15 | [44100](https://github.com/airbytehq/airbyte/pull/44100) | Refactor connector to manifest-only format |
| 0.1.16 | 2024-08-10 | [43593](https://github.com/airbytehq/airbyte/pull/43593) | Update dependencies |
| 0.1.15 | 2024-08-03 | [43114](https://github.com/airbytehq/airbyte/pull/43114) | Update dependencies |
| 0.1.14 | 2024-07-27 | [42791](https://github.com/airbytehq/airbyte/pull/42791) | Update dependencies |
| 0.1.13 | 2024-07-20 | [42236](https://github.com/airbytehq/airbyte/pull/42236) | Update dependencies |
| 0.1.12 | 2024-07-13 | [41769](https://github.com/airbytehq/airbyte/pull/41769) | Update dependencies |
| 0.1.11 | 2024-07-10 | [41421](https://github.com/airbytehq/airbyte/pull/41421) | Update dependencies |
| 0.1.10 | 2024-07-09 | [41094](https://github.com/airbytehq/airbyte/pull/41094) | Update dependencies |
| 0.1.9 | 2024-07-06 | [40984](https://github.com/airbytehq/airbyte/pull/40984) | Update dependencies |
| 0.1.8 | 2024-06-25 | [40343](https://github.com/airbytehq/airbyte/pull/40343) | Update dependencies |
| 0.1.7 | 2024-06-22 | [40105](https://github.com/airbytehq/airbyte/pull/40105) | Update dependencies |
| 0.1.6 | 2024-06-04 | [39069](https://github.com/airbytehq/airbyte/pull/39069) | [autopull] Upgrade base image to v1.2.1 |
| 0.1.5 | 2024-05-20 | [38226](https://github.com/airbytehq/airbyte/pull/38226) | Make connector compatible with the builder |
| 0.1.4 | 2024-04-19 | [37220](https://github.com/airbytehq/airbyte/pull/37220) | Updating to 0.80.0 CDK |
| 0.1.3 | 2024-04-18 | [37220](https://github.com/airbytehq/airbyte/pull/37220) | Manage dependencies with Poetry. |
| 0.1.2 | 2024-04-15 | [37220](https://github.com/airbytehq/airbyte/pull/37220) | Base image migration: remove Dockerfile and use the python-connector-base image |
| 0.1.1 | 2024-04-12 | [37220](https://github.com/airbytehq/airbyte/pull/37220) | schema descriptions |
| 0.1.0 | 2023-03-14 | [3563](https://github.com/airbytehq/airbyte/pull/3563) | Initial Release |

</details>
