package com.chun.ailatrieuphu1.model

data class Question(
    var question: String,
    var level: Int,
    var caseA: String,
    var caseB: String,
    var caseC: String,
    var caseD: String,
    var trueCase: Int
)