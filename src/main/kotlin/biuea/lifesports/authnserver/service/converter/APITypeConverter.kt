package biuea.lifesports.authnserver.service.converter

import biuea.lifesports.authnserver.common.exception.UnauthorizedException
import biuea.lifesports.authnserver.service.constants.APIType
import biuea.lifesports.authnserver.service.error.AuthnErrors
import javax.persistence.AttributeConverter

class APITypeConverter: AttributeConverter<APIType, String> {
    override fun convertToDatabaseColumn(attribute: APIType): String {
        TODO("Not yet implemented")
    }

    override fun convertToEntityAttribute(dbData: String?): APIType {
        return APIType.values().find { it.name == dbData }?: throw UnauthorizedException(error = AuthnErrors.of(error = AuthnErrors.HAS_NOT_TOKEN))
    }
}