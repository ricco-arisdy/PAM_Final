package com.example.pam_final.ui.edit

import com.example.pam_final.navigation.DestinasiNavigasi

object EditDestination : DestinasiNavigasi {
    override val route = "item_edit"
    override val titleRes ="Edit Kontak"
    const val kontakId = "itemId"
    val routeWithArgs = "${EditDestination.route}/{$kontakId}"
}