package com.example.turismoapp.feature.profile.data.repository

import com.example.turismoapp.feature.profile.domain.model.ProfileModel
import com.example.turismoapp.feature.profile.domain.repository.IProfileRepository
import com.example.turismoapp.feature.profile.domain.vo.EmailAddress
import com.example.turismoapp.feature.profile.domain.vo.PersonName
import com.example.turismoapp.feature.profile.domain.vo.PhoneNumber
import com.example.turismoapp.feature.profile.domain.vo.SummaryText
import com.example.turismoapp.feature.profile.domain.vo.UrlPath

class ProfileRepository: IProfileRepository {
    override fun fetchData(): Result<ProfileModel> {
        return Result.success(
            ProfileModel(
                // CAMBIO: Usar .value para obtener el String de cada Value Object
                name = PersonName.of("Vivi Revollo").value,
                email = EmailAddress.of("vivi.omg@gmail.com").value,
                cellphone = PhoneNumber.of("+777 7777").value,
                pathUrl = UrlPath.of("https://upload.wikimedia.org/wikipedia/commons/e/e0/Portrait_of_a_Person.jpg").value,
                summary = SummaryText.of("¡Ahora el ViewModel funciona!").value
            )
        )
    }
}