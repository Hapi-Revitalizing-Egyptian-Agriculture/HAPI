package com.example.hapi.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hapi.R
import com.example.hapi.ui.theme.WarningColor
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.util.ScreenSize
import com.example.hapi.util.getScreenWidth

@Composable
fun RedWarningBox(
    modifier: Modifier = Modifier,
    width: Dp,
    warningText: String
) {

    val fontSize = when (getScreenWidth(width)) {
        ScreenSize.SMALL -> 8.sp
        ScreenSize.NORMAL -> 10.sp
        ScreenSize.LARGE -> 12.sp
        ScreenSize.XLARGE -> 14.sp
    }
    val iconSize = when (getScreenWidth(width)) {
        ScreenSize.SMALL -> 14
        ScreenSize.NORMAL -> 16
        ScreenSize.LARGE -> 18
        ScreenSize.XLARGE -> 20
    }
    if (warningText.isEmpty()) {
        Box(modifier = Modifier.padding(10.dp))
    } else {
        Row(
            modifier = modifier
                .padding(top = 2.dp, bottom = 5.dp, start = 1.dp, end = 1.dp)
                .background(WarningColor)
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(6.dp)),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(iconSize.dp)
                    .fillMaxHeight()
                    .padding(end = 2.dp),
                imageVector = Icons.Default.Error,
                contentDescription = null,
                tint = YellowAppColor
            )
            Text(
                modifier = Modifier
                    .padding(vertical = 3.dp),
                text = warningText,
                color = YellowAppColor,
                fontSize = fontSize,
                fontFamily = FontFamily(
                    Font(
                        R.font.poppins_semibold
                    )
                )
            )
        }
    }
}

@Preview
@Composable
fun WarningBoxPreview() {
    RedWarningBox(
        width = 100.dp,
        warningText = "THIS USERNAME ALREADY EXISTS"
    )
}