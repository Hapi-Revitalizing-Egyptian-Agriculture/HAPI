package com.example.hapi.presentation.progress

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.R
import com.example.hapi.presentation.auth.common.Logo
import com.example.hapi.presentation.home.farmer.navigateToFarmerHome
import com.example.hapi.presentation.home.landowner.navigateToLandownerHome
import com.example.hapi.presentation.identityselection.Crops
import com.example.hapi.presentation.identityselection.navigateToIdentitySelection
import com.example.hapi.presentation.main.MainViewModel
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.Dimens
import com.example.hapi.util.Tab

@Composable
fun ProgressScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    final: String = "false",
    isFarmer: Boolean = false
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
    ) {

        val topGuideLine = createGuidelineFromTop(.1f)
        val (logoBox, cropBox, contentBox) = createRefs()

        Logo(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(logoBox) {
                top.linkTo(topGuideLine)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }

        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
                .constrainAs(contentBox) {
                    top.linkTo(logoBox.bottom, margin = Dimens.content_margin)
                    bottom.linkTo(cropBox.top, margin = Dimens.content_margin)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            contentAlignment = Alignment.Center
        ) {
            if (final == "true") {
                SetupMessage(
                    message = stringResource(id = R.string.congratulation)
                ) {
                    mainViewModel.setSelectedTab(Tab.HOME)
                    if (isFarmer)
                        navController.navigateToFarmerHome()
                    else
                        navController.navigateToLandownerHome()
                }
            } else {
                SetupMessage(
                    message = stringResource(id = R.string.account_setup)
                ) {
                    navController.navigateToIdentitySelection()
                }
            }

        }
        Crops(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(cropBox) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
    }
}

@Preview
@Composable
private fun ProgressPreview() {
    ProgressScreen(rememberNavController())
}