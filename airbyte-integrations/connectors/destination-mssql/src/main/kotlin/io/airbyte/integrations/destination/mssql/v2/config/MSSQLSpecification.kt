/*
 * Copyright (c) 2024 Airbyte, Inc., all rights reserved.
 */

package io.airbyte.integrations.destination.mssql.v2.config

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyDescription
import com.fasterxml.jackson.annotation.JsonSetter
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaDescription
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaInject
import com.kjetland.jackson.jsonSchema.annotations.JsonSchemaTitle
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings
import io.airbyte.cdk.command.ConfigurationSpecification
import io.airbyte.cdk.load.spec.DestinationSpecificationExtension
import io.airbyte.cdk.ssh.MicronautPropertiesFriendlySshTunnelMethodConfigurationSpecification
import io.airbyte.cdk.ssh.SshTunnelMethodConfiguration
import io.airbyte.protocol.models.v0.DestinationSyncMode
import io.micronaut.context.annotation.ConfigurationBuilder
import jakarta.inject.Singleton

@Singleton
@JsonSchemaTitle("MSSQL Destination Specification")
@SuppressFBWarnings("NP_NONNULL_RETURN_VIOLATION")
class MSSQLSpecification : ConfigurationSpecification(), LoadTypeSpecification {
    @get:JsonSchemaTitle("Host")
    @get:JsonPropertyDescription("The host name of the MSSQL database.")
    @get:JsonProperty("host")
    @get:JsonSchemaInject(json = """{"order":0}""")
    val host: String = ""

    @get:JsonSchemaTitle("Port")
    @get:JsonPropertyDescription("The port of the MSSQL database.")
    @get:JsonProperty("port")
    @get:JsonSchemaInject(json = """{"minimum":0,"maximum":65536,"examples":["1433"],"order":1}""")
    val port: Int = 1433

    @get:JsonSchemaTitle("DB Name")
    @get:JsonPropertyDescription("The name of the MSSQL database.")
    @get:JsonProperty("database")
    @get:JsonSchemaInject(json = """{"order":2}""")
    val database: String = ""

    @get:JsonSchemaTitle("Default Schema")
    @get:JsonPropertyDescription(
        "The default schema tables are written to if the source does not specify a namespace. The usual value for this field is \"public\"."
    )
    @get:JsonProperty("schema")
    @get:JsonSchemaInject(json = """{"examples":["public"],"default":"public","order":3}""")
    val schema: String = "public"

    @get:JsonSchemaTitle("User")
    @get:JsonPropertyDescription("The username which is used to access the database.")
    @get:JsonProperty("user")
    @get:JsonSchemaInject(json = """{"order":4}""")
    val user: String = "user"

    @get:JsonSchemaTitle("Password")
    @get:JsonPropertyDescription("The password associated with this username.")
    @get:JsonProperty("password")
    @get:JsonSchemaInject(json = """{"airbyte_secret":true,"order":5}""")
    val password: String? = null

    @get:JsonSchemaTitle("JDBC URL Params")
    @get:JsonPropertyDescription(
        "Additional properties to pass to the JDBC URL string when connecting to the database formatted as 'key=value' pairs separated by the symbol '&'. (example: key1=value1&key2=value2&key3=value3)."
    )
    @get:JsonProperty("jdbc_url_params")
    @get:JsonSchemaInject(json = """{"order":6}""")
    val jdbcUrlParams: String? = null

    @get:JsonSchemaTitle("SSL Method")
    @get:JsonPropertyDescription(
        "The encryption method which is used to communicate with the database."
    )
    @get:JsonProperty("ssl_method")
    @get:JsonSchemaInject(json = """{"order":7}""")
    lateinit var sslMethod: EncryptionMethod

    @get:JsonProperty("load_type")
    @get:JsonSchemaInject(json = """{"always_show": true,"order":8}""")
    override val loadType: LoadType = InsertLoadSpecification()

    @JsonIgnore
    @ConfigurationBuilder(configurationPrefix = "tunnel_method")
    val tunnelMethod = MicronautPropertiesFriendlySshTunnelMethodConfigurationSpecification()

    @JsonIgnore var tunnelMethodJson: SshTunnelMethodConfiguration? = null

    @JsonSetter("tunnel_method")
    fun setTunnelMethodValue(value: SshTunnelMethodConfiguration?) {
        tunnelMethodJson = value
    }

    @JsonGetter("tunnel_method")
    @JsonSchemaTitle("SSH Tunnel Method")
    @JsonPropertyDescription(
        "Whether to initiate an SSH tunnel before connecting to the database," +
            " and if so, which kind of authentication to use.",
    )
    @JsonSchemaInject(json = """{"order":5}""")
    fun getTunnelMethodValue(): SshTunnelMethodConfiguration? =
        tunnelMethodJson ?: tunnelMethod.asSshTunnelMethod()
}

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "name")
@JsonSubTypes(
    JsonSubTypes.Type(value = Unencrypted::class, name = Unencrypted.NAME),
    JsonSubTypes.Type(value = EncryptedTrust::class, name = EncryptedTrust.NAME),
    JsonSubTypes.Type(value = EncryptedVerify::class, name = EncryptedVerify.NAME),
)
sealed interface EncryptionMethod {
    @get:JsonProperty("name") val name: String
}

@JsonSchemaTitle("Unencrypted")
@JsonSchemaDescription("The data transfer will not be encrypted.")
class Unencrypted : EncryptionMethod {
    companion object {
        const val NAME = "unencrypted"
    }
    override val name: String = NAME
}

@JsonSchemaTitle("Encrypted (trust server certificate)")
@JsonSchemaDescription(
    "Use the certificate provided by the server without verification. (For testing purposes only!)"
)
class EncryptedTrust : EncryptionMethod {
    companion object {
        const val NAME = "encrypted_trust_server_certificate"
    }
    override val name: String = NAME
}

@JsonSchemaTitle("Encrypted (verify certificate)")
@JsonSchemaDescription("Verify and use the certificate provided by the server.")
class EncryptedVerify(
    @get:JsonSchemaTitle("Trust Store Name")
    @get:JsonPropertyDescription("Specifies the name of the trust store.")
    @get:JsonProperty("trustStoreName")
    @get:JsonSchemaInject(json = """{"order":1}""")
    val trustStoreName: String? = null,
    @get:JsonSchemaTitle("Trust Store Password")
    @get:JsonPropertyDescription("Specifies the password of the trust store.")
    @get:JsonProperty("trustStorePassword")
    @get:JsonSchemaInject(json = """{"airbyte_secret":true,"order":2}""")
    val trustStorePassword: String? = null,
    @get:JsonSchemaTitle("Host Name In Certificate")
    @get:JsonPropertyDescription(
        "Specifies the host name of the server. The value of this property must match the subject property of the certificate."
    )
    @get:JsonProperty("hostNameInCertificate")
    @get:JsonSchemaInject(json = """{"order":3}""")
    val hostNameInCertificate: String? = null,
) : EncryptionMethod {
    companion object {
        const val NAME = "encrypted_verify_certificate"
    }
    override val name: String = NAME
}

@Singleton
class MSSQLSpecificationExtension : DestinationSpecificationExtension {

    override val supportedSyncModes =
        listOf(
            DestinationSyncMode.OVERWRITE,
            DestinationSyncMode.APPEND,
            DestinationSyncMode.APPEND_DEDUP
        )
    override val supportsIncremental = true
}
