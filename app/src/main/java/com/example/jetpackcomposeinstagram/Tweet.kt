package com.example.jetpackcomposeinstagram

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Tweet(modifier: Modifier) {
    var chat by rememberSaveable { mutableStateOf(false) }
    var retweet by rememberSaveable { mutableStateOf(false) }
    var like by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = modifier
            .background(Color(0xFF161D26))
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Row(
            modifier = modifier
                .background(Color(0xFF161D26))
        ) {
            Image(
                painter = painterResource(R.drawable.profile),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .size(56.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    TextTitle("SValac")
                    DefaultText("@Shiro.Valac", modifier = Modifier.padding(end = 8.dp))
                    DefaultText("10 hrs")
                    Spacer(Modifier.weight(1f))
                    Icon(
                        painter = painterResource(R.drawable.ic_dots),
                        contentDescription = "options",
                        tint = Color.White
                    )
                }
                Spacer(Modifier.padding(8.dp))
                TextBody(
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer sodales\n" +
                            "laoreet commodo. Phasellus a purus eu risus elementum consequat. Aenean eu\n" +
                            "elit ut nunc convallis laoreet non ut libero. Suspendisse interdum placerat\n" +
                            "risus vel ornare. Donec vehicula, turpis sed consectetur ullamcorper, ante\n" +
                            "nunc egestas quam, ultricies adipiscing velit enim at nunc. Aenean id diam\n" +
                            "neque. Praesent ut lacus sed justo viverra fermentum et ut sem."
                )
                Spacer(Modifier.padding(8.dp))
                Image(
                    painter = painterResource(R.drawable.profile),
                    contentDescription = "Tweet Image",
                    modifier = Modifier.clip(shape = RoundedCornerShape(10)),
                    contentScale = ContentScale.FillWidth,
                )
                Row(modifier = Modifier.padding(top = 16.dp)) {
                    SocialIcon(
                        modifier = Modifier.weight(1f),
                        unselectedIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_chat),
                                contentDescription = "Social Icon",
                                tint = Color(0xFF7E8B98)
                            )
                        },
                        selectedIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_chat_filled),
                                contentDescription = "Social Icon",
                                tint = Color.Cyan
                            )
                        },
                        isSelected = chat
                    ) { chat = !chat }
                    SocialIcon(
                        modifier = Modifier.weight(1f),
                        unselectedIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_share),
                                contentDescription = "Social Icon",
                                tint = Color(0xFF7E8B98)
                            )
                        },
                        selectedIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_share),
                                contentDescription = "Social Icon",
                                tint = Color.Green
                            )
                        },
                        isSelected = retweet
                    ) { retweet = !retweet }
                    SocialIcon(
                        modifier = Modifier.weight(1f),
                        unselectedIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_like),
                                contentDescription = "Social Icon",
                                tint = Color(0xFF7E8B98)
                            )
                        },
                        selectedIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_like_filled),
                                contentDescription = "Social Icon",
                                tint = Color.Red
                            )
                        },
                        isSelected = like
                    ) { like = !like }
                }
            }
        }
                TweetDivider(modifier = modifier)
    }


}

@Composable
fun SocialIcon(
    modifier: Modifier,
    unselectedIcon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit,
    isSelected: Boolean,
    onItemSelected: () -> Unit
) {
    val defaultValue = 1
    Row(
        modifier = modifier.clickable { onItemSelected() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isSelected) {
            selectedIcon()
        } else {
            unselectedIcon()
        }
        Text(
            text = if (isSelected) {
                "${(defaultValue + 1)}"
            } else {
                "$defaultValue"
            },
            color = Color(0xFF7E8B98),
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}

@Composable
fun TextBody(text: String, modifier: Modifier = Modifier) {
    Text(text = text, modifier = modifier, color = Color.White)

}

@Composable
fun TextTitle(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        modifier = modifier.padding(end = 8.dp),
        fontWeight = FontWeight.Bold,
        color = Color.White
    )
}

@Composable
fun DefaultText(title: String, modifier: Modifier = Modifier) {
    Text(text = title, modifier = modifier, color = Color.Gray)
}

@Composable
fun TweetDivider(modifier: Modifier){
    HorizontalDivider(thickness = 1.dp)
}