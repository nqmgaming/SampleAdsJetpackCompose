package com.nqmgaming.sampleadsjetpackcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.ads.MobileAds
import com.nqmgaming.sampleadsjetpackcompose.AdUtils.BannerTestAds
import com.nqmgaming.sampleadsjetpackcompose.AdUtils.interstitialTestAd
import com.nqmgaming.sampleadsjetpackcompose.AdUtils.rewardedInterstitialTestAd
import com.nqmgaming.sampleadsjetpackcompose.AdUtils.rewardedTestAd
import com.nqmgaming.sampleadsjetpackcompose.ui.theme.SampleAdsJetpackComposeTheme
import kotlin.random.Random

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MobileAds.initialize(this) {
            // Initialization done
        }
        enableEdgeToEdge()
        setContent {
            SampleAdsJetpackComposeTheme {
                TestScreen(activity = this@MainActivity)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestScreen(modifier: Modifier = Modifier, activity: MainActivity) {
    val adPositions = listOf(20, 40, 60, 80) // Positions where you want to show ads

    // Current money
    var money by remember { mutableIntStateOf(0) }
    var moneyInterstitial by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Sample Ads Jetpack Compose") }
            )
        },
        bottomBar = {
            BannerTestAds()
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
//            LazyColumn {
//                items(100) { index ->
//                    if (index in adPositions) { // Check if the current position is in the adPositions list
//                        BannerTestAds()
//                    } else {
//                        Text(
//                            text = "Item $index",
//                            style = TextStyle(fontSize = 20.sp),
//                            modifier = Modifier.padding(16.dp)
//                        )
//                    }
//                }
//            }

            Text(
                text = "Current Money: $money",
                style = TextStyle(fontSize = 20.sp),
                modifier = Modifier.padding(16.dp)
            )

            Text(
                text = "Current Money Interstitial: $moneyInterstitial",
                style = TextStyle(fontSize = 20.sp),
                modifier = Modifier.padding(16.dp)
            )

            Button(
                onClick = {
                    interstitialTestAd(activity)
                }
            ) {
                Text(text = "Show Interstitial Ad")
            }

            Button(
                onClick = {
                    rewardedTestAd(activity) {
                        money += Random.nextInt(1, 10)
                    }
                }
            ) {
                Text(text = "Show Rewarded Ad")
            }

            Button(
                onClick = {
                    rewardedInterstitialTestAd(activity) {
                        moneyInterstitial += Random.nextInt(1, 10)
                    }
                }
            ) {
                Text(text = "Show Rewarded Interstitial Ad")
            }
        }

    }
}
