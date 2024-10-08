package com.example.hapi.presentation.home.detectionhistory.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.R
import com.example.hapi.presentation.common.NavHeader
import com.example.hapi.presentation.home.common.DetectionHistoryCard
import com.example.hapi.presentation.home.common.HistoryWarning
import com.example.hapi.presentation.home.detectiondetails.ui.navigateToDetectionDetails
import com.example.hapi.presentation.home.detectionhistory.viewmodel.DetectionHistoryViewModel
import com.example.hapi.presentation.main.MainViewModel
import com.example.hapi.presentation.settings.language.LanguageViewModel
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.ENGLISH
import com.example.hapi.util.Tab
import com.example.hapi.util.isNetworkConnected

@Composable
fun DetectionHistory(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    detectionHistoryViewmodel: DetectionHistoryViewModel = hiltViewModel(),
    languageViewModel: LanguageViewModel = hiltViewModel()
) {

    val isEnglish = languageViewModel.appLanguage.collectAsState().value == ENGLISH
    var isNetworkConnected by remember {
        mutableStateOf(true)
    }
    val isAllDetectionsSelected =
        detectionHistoryViewmodel.isAllDetectionsSelected.collectAsState().value

    LaunchedEffect(isAllDetectionsSelected, isEnglish) {
        isNetworkConnected = isNetworkConnected()
        if (isAllDetectionsSelected)
            detectionHistoryViewmodel.getDetectionHistory()
        else
            detectionHistoryViewmodel.getDetectionHistoryByUsername()
    }

    val detectionHistoryList = detectionHistoryViewmodel.detectionList.collectAsState().value

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
    ) {

        val maxHeight = maxHeight
        val maxWidth = maxWidth

        val padding = if (maxWidth > 300.dp) 16.dp else 12.dp
        val contentHorizontalPadding = maxWidth* 0.06f
        val backIconSize = if (maxHeight < 650.dp) 60 else 75
        val verticalPadding = maxHeight*.053f


        Column(
            modifier = Modifier
                .fillMaxSize().padding(horizontal = padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            NavHeader(
                modifier = Modifier.padding(vertical = 12.dp),
                topText = stringResource(id = R.string.detection),
                downText = stringResource(id = R.string.history),
                imageId = if (isEnglish) R.drawable.back_home else R.drawable.home_back_btn_ar,
                imageSize = backIconSize
            ) {
                mainViewModel.setSelectedTab(Tab.HOME)
                navController.popBackStack()
            }

            Spacer(modifier = Modifier.height(verticalPadding))

            DetectionHistoryFilters(
                onAllDetectionsSelected = {
                    detectionHistoryViewmodel.modifyIsAllDetectionsSelected(true)
                },
                onYourDetectionsSelected = {
                    detectionHistoryViewmodel.modifyIsAllDetectionsSelected(false)
                }
            )

            Spacer(modifier = Modifier.height(verticalPadding))

            if (detectionHistoryList.isEmpty())
                HistoryWarning(
                    topMsg = R.string.not_detections,
                    downMsg = R.string.click_on_camera,
                )
            else {
                LazyColumn(
                    modifier = Modifier
                        .padding(horizontal = contentHorizontalPadding)
                        .fillMaxWidth()
                ) {
                    items(detectionHistoryList.size) { index ->
                        val detection = detectionHistoryList[index]
                        DetectionHistoryCard(
                            modifier = Modifier.padding(vertical = 10.dp),
                            username = detection.username,
                            date = detection.date,
                            time = detection.time,
                            imageUrl = if (isNetworkConnected) detection.imageUrl else ""
                        ) {
                            navController.navigateToDetectionDetails(
                                id = detection.remoteId.toString()
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(verticalPadding))
            }
        }
    }
}

@Preview
@Composable
fun DetectionHistoryPreview() {
    DetectionHistory(rememberNavController())
}