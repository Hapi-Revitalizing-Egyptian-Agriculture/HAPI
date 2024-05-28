package com.example.hapi.presentation.settings.landfarmers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.R
import com.example.hapi.presentation.auth.common.NavHeader
import com.example.hapi.presentation.main.MainViewModel
import com.example.hapi.presentation.settings.language.LanguageViewModel
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.Tab
import com.example.hapi.util.isNetworkConnected

@Composable
fun LandFarmers(
    farmersViewModel: LandFarmersViewModel = hiltViewModel(),
    mainViewModel: MainViewModel = hiltViewModel(),
    languageViewModel: LanguageViewModel = hiltViewModel(),
    navController: NavController
) {

    val isEnglish = languageViewModel.isEnglishIsSelected.collectAsState().value

    LaunchedEffect(key1 = Unit) {
        if (isNetworkConnected()) {
            farmersViewModel.fetchFarmers()
        } else {
            farmersViewModel.getFarmers()
        }

    }
    val farmers = farmersViewModel.farmersList.collectAsState().value

    LaunchedEffect(key1 = Unit) {
        farmersViewModel.fetchFarmers()
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
            .padding(bottom = 26.dp)
    ) {

        val (navHeader, content) = createRefs()
        val topGuideLine = createGuidelineFromTop(.05f)

        NavHeader(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .constrainAs(navHeader) {
                    top.linkTo(topGuideLine)
                },
            topText = stringResource(id = R.string.listOf),
            downText = stringResource(id = R.string.farmers),
            imageId = if(isEnglish) R.drawable.settings_back_btn else R.drawable.settings_back_btn_ar
        ) {
            mainViewModel.setSelectedTab(Tab.SETTINGS)
            navController.popBackStack()
        }

        if (farmers.isEmpty()) {
            NoFarmers(
                modifier = Modifier.constrainAs(content) {
                    top.linkTo(navHeader.bottom, margin = 44.dp)
                }
            )
        } else {
            FarmersGridList(
                farmers = farmers,
                modifier = Modifier.constrainAs(content) {
                    top.linkTo(navHeader.bottom, margin = 44.dp)
                }
            )
        }
    }
}

@Preview
@Composable
fun FarmersListPreview() {
    LandFarmers(navController = rememberNavController())
}