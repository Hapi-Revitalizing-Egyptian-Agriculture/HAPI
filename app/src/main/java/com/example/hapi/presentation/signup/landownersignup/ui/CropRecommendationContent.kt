package com.example.hapi.presentation.signup.landownersignup.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hapi.R
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.ui.theme.YellowAppColor


@Composable
fun CropRecommendationContent(
    modifier: Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(title = stringResource(id = R.string.do_you))
        RecommendationRaw(
            cardText = stringResource(id = R.string.recommend_crop),
            description = stringResource(id = R.string.use_recommendation)
        ) {

        }
        RecommendationRaw(
            cardText = stringResource(id = R.string.have_crop),
            description = stringResource(id = R.string.choose_crop)
        ) {

        }
    }
}

@Composable
private fun RecommendationRaw(
    cardText: String,
    description: String,
    onCardClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 26.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RecommendationCard(
            modifier = Modifier.weight(1f),
            text = cardText
        ) {
            onCardClick()
        }
        DescriptionText(
            modifier = Modifier.weight(1.4f),
            text = description
        )
    }
}

@Composable
private fun Title(
    title: String
) {
    Text(
        modifier = Modifier.padding(bottom = 25.dp),
        color = YellowAppColor,
        fontSize = 16.sp,
        fontFamily = FontFamily(
            Font(
                R.font.poppins_black
            )
        ),
        text = title,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun RecommendationCard(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .padding(end = 11.dp)
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = YellowAppColor
        )
    ) {
        Box(
            modifier = Modifier
                .padding(vertical = 30.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier,
                color = GreenAppColor,
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(
                        R.font.poppins_black
                    )
                ),
                text = text,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun DescriptionText(
    modifier: Modifier,
    text: String
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            color = YellowAppColor,
            fontSize = 13.sp,
            fontFamily = FontFamily(
                Font(
                    R.font.poppins_bold
                )
            ),
            text = text,
            textAlign = TextAlign.Start
        )
    }
}

@Preview
@Composable
private fun ContentPreview() {
    CropRecommendationContent(Modifier)
}