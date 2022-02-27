package com.example.final_pratical_exam.entities

import java.io.Serializable

data class Menu(
     val MenuImage: String?,
     var MenuTitle: String?,
     var Price: Double?,
     var Quantity: Int?,
     var Description: String?,
):Serializable {
}