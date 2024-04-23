package com.x.models;

import com.google.gson.annotations.SerializedName;

public record ErrorResponse(
        String result,
        String documentation,
        @SerializedName("terms-of-use") String termsOfUse,
        @SerializedName("error-type") String errorType) {
}
