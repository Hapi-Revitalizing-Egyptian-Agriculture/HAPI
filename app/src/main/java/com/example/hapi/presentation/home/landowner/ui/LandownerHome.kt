package com.example.hapi.presentation.home.landowner.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.R
import com.example.hapi.domain.model.LandAction
import com.example.hapi.presentation.common.NavHeader
import com.example.hapi.presentation.home.common.VerticalHistoryCard
import com.example.hapi.presentation.home.detectiondetails.ui.navigateToDetectionDetails
import com.example.hapi.presentation.home.detectionhistory.ui.navigateToDetectionHistory
import com.example.hapi.presentation.home.landhistory.ui.navigateToLandHistory
import com.example.hapi.presentation.home.landowner.viewmodel.LandownerHomeViewModel
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.Dimens
import com.example.hapi.util.isNetworkConnected

@Composable
fun LandownerHome(
    navController: NavController,
    viewModel: LandownerHomeViewModel = hiltViewModel()
) {
    var isNetworkConnected by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(Unit) {
        isNetworkConnected = isNetworkConnected()
        if (isNetworkConnected()) {
            viewModel.fetchDetectionHistory()
            viewModel.fetchLandHistory()
            viewModel.fetchTanksData()
            viewModel.getRemoteLastFarmer()
        }
        viewModel.getLastDetection()
        viewModel.getLastLandHistoryItem()
        viewModel.getTanksData()
    }

    val username = viewModel.username.collectAsState().value
    val detectionDate = viewModel.detectionDate.collectAsState().value
    val detectionTime = viewModel.detectionTime.collectAsState().value
    val detectionRemoteId = viewModel.detectionRemoteId.collectAsState().value
    val detectionUsername = viewModel.detectionUsername.collectAsState().value
    val imageUrl = viewModel.imageUrl.collectAsState().value
    val landActionType = viewModel.landActionType.collectAsState().value
    val landActionDate = viewModel.landActionDate.collectAsState().value
    val landActionTime = viewModel.landActionTime.collectAsState().value
    val waterLevel = viewModel.waterLevel.collectAsState().value
    val crop = viewModel.crop.collectAsState().value
    val lastFarmerUsername = viewModel.lastFarmerUsername.collectAsState().value
    val lastFarmerDate = viewModel.lastFarmerDate.collectAsState().value
    val lastFarmerTime = viewModel.lastFarmerTime.collectAsState().value
    val nitrogen = viewModel.nitrogen.collectAsState().value
    val phosphorus = viewModel.phosphorus.collectAsState().value
    val potassium = viewModel.potassium.collectAsState().value

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
    ) {

        val (welcomeHeader, dataHeader, content, historyCards) = createRefs()
        val topGuideLine = createGuidelineFromTop(Dimens.top_guideline_home)

        NavHeader(
            modifier = Modifier
                .padding(horizontal = Dimens.nav_header_horizontal_padding)
                .constrainAs(welcomeHeader) {
                    top.linkTo(topGuideLine)
                    bottom.linkTo(dataHeader.top)
                },
            imageId = R.drawable.logo,
            topText = stringResource(id = R.string.welcome_home),
            downText = username,
        )

        TanksData(
            modifier = Modifier.constrainAs(dataHeader) {
                top.linkTo(welcomeHeader.bottom, margin = 16.dp)
                bottom.linkTo(content.top)
            },
            crop = crop,
            waterTankLevel = waterLevel,
            nTankLevel = nitrogen.toInt(),
            pTankLevel = phosphorus.toInt(),
            kTankLevel = potassium.toInt()
        )
        LandownerHomeLandData(
            modifier = Modifier
                .padding(horizontal = 35.dp)
                .constrainAs(content) {
                    top.linkTo(dataHeader.bottom,margin = 16.dp)
                    bottom.linkTo(historyCards.top)
                },
            lastLandAction = LandAction(
                name = landActionType.uppercase(), date = landActionDate, time = landActionTime
            ),
            detectionUsername = detectionUsername,
            detectionDate = detectionDate,
            detectionTime = detectionTime,
            imageUrl = if (isNetworkConnected) imageUrl else "",
            lastFarmerDate = lastFarmerDate,
            lastFarmerTime = lastFarmerTime,
            lastFarmerUsername = lastFarmerUsername
        ) {
            navController.navigateToDetectionDetails(
                id = detectionRemoteId.toString()
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 35.dp)
                .constrainAs(historyCards) {
                    top.linkTo(content.bottom)
                    bottom.linkTo(parent.bottom, margin = 33.dp)
                },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            VerticalHistoryCard(
                isLand = true,
                modifier = Modifier.weight(1f)
            ) { navController.navigateToLandHistory() }
            Spacer(modifier = Modifier.width(10.dp).weight(0.2f))
            VerticalHistoryCard(
                isLand = false,
                modifier = Modifier.weight(1f)
            ) { navController.navigateToDetectionHistory() }
        }
    }
}


@Preview
@Composable
fun LandownerHomePreview() {
    LandownerHome(
        rememberNavController()
    )
}