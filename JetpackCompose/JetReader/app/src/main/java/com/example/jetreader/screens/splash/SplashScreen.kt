package com.example.jetreader.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetreader.components.ReaderLogo
import com.example.jetreader.navigation.AppScreens
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

@Preview
@Composable
fun SplashScree(navController: NavController = NavController(context = LocalContext.current)) {

    val scale = remember {
        Animatable(0f)
    }
    
    LaunchedEffect(key1 = true){
        scale.animateTo(0.9f,
                        animationSpec = tween(1000, easing = {
                            OvershootInterpolator(8f)
                                .getInterpolation(it)
                        }))
        delay(2000L)
        if(FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()){
            navController.navigate(AppScreens.LoginScreen.name)
        }
        else{
            navController.navigate(AppScreens.HomeScreen.name)
        }
    }



    Surface(
        modifier = Modifier
            .padding(16.dp)
            .size(330.dp)
            .scale(scale.value),
        shape = CircleShape,
        color = Color.White,
        border = BorderStroke(3.dp, color = Color.LightGray)
        ) {

        Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(1.dp)) {
            ReaderLogo()
            Spacer(modifier = Modifier.height(15.dp))
            
            Text(text = "\"Read. Change. Yourself\"",
                    style = MaterialTheme.typography.h5,
                color = Color.LightGray)
        }
    }
}