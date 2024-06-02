package com.danya.app.util

// take string, if it's a float, trip decimals if they are not 0
fun String.trimDecimals(): String {
    return if (this.contains(".")) {
        val trimmed = this.trimEnd('0')
        if (trimmed.endsWith('.')) {
            trimmed.dropLast(1)
        } else {
            trimmed
        }
    } else {
        this
    }
}

// generate a Firestore rule to only take StockpileItemModels that belong to the user
fun String.generateFirestoreRule(): String {
    return """
        |service cloud.firestore {
        |  match /databases/{database}/documents {
        |    match /$this/{document=**} {
        |      allow read, write: if request.auth != null && request.auth.uid == resource.data.userId;
        |    }
        |  }
        |}
    """.trimMargin()
}