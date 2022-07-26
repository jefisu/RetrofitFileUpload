package com.jefisu.retrofitfileupload

import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class FileRepository {

    suspend fun uploadImage(file: File): String {
        return try {
            FileApi.instance.uploadImage(
                image = MultipartBody.Part
                    .createFormData(
                        "image",
                        file.name,
                        file.asRequestBody()
                    )
            ).body() ?: "There was an error sending the file"
        } catch (e: Exception) {
            e.printStackTrace().toString()
        }
    }
}