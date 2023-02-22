package com.example.bizcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bizcard.ui.theme.BizCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BizCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    createBizCard()
                }
            }
        }
    }
}

@Composable
fun createBizCard(){
    var buttonClickedState = remember{
        mutableStateOf(false)
    }

    androidx.compose.material.Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {

        
        Card(modifier = Modifier
            .width(200.dp)
            .height(390.dp)
            .padding(12.dp),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            elevation = 4.dp,) {

            Column(modifier = Modifier.height(300.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally){
                CreateProfileImage()
                Divider(modifier = Modifier,
                        color = Color.LightGray,
                        thickness = 1.dp,
                )
                 CreateBio()
                
                Button(onClick = {
                    buttonClickedState.value = !buttonClickedState.value

                }) {
                    Text(text = "Portfolio",
                        style = MaterialTheme.typography.button)

                }
                if(buttonClickedState.value){
                    Content()
                }
                else{
                    Box() {
                        
                    }
                }

            }
        }
    }
}

@Preview
@Composable
fun Content(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(5.dp)){
        
        Surface(modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(2.dp, color = Color.LightGray)
        ) {

            Portfolio(data = listOf("Project 1", "Project 2", "Project 3","Project 4", "Project 5", "Project 6"))

        }
        
    }
}

@Composable
fun Portfolio(data: List<String>) {
    LazyColumn{
        items(data){ item ->
           Card(modifier = Modifier
               .padding(13.dp)
               .fillMaxWidth(),
                shape = RectangleShape,
               elevation = 4.dp
           ) {
               Row(modifier = Modifier
                   .padding(8.dp)
                   .fillMaxWidth()
                   .background(color = MaterialTheme.colors.surface)
                   .padding(7.dp)
               ){
                   CreateProfileImage(Modifier.size(90.dp))
                   Column(modifier = Modifier.padding(7.dp)
                       .align(alignment = Alignment.CenterVertically)) {
                      
                       Text(text = item, fontWeight = FontWeight.Bold)
                       Text(text = "A Great Project", style = MaterialTheme.typography.body2)
                   }

               }

           }
        }
    }

}


@Composable
private fun CreateBio() {
    Column(modifier = Modifier.padding(5.dp)) {
        Text(
            text = "Pritam S.",
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.primaryVariant
        )

        Text(
            text = "Android Compose Programemr",
            modifier = Modifier.padding(3.dp)
        )

        Text(
            text = "@pritamSankhla",
            modifier = Modifier.padding(3.dp),
            style = MaterialTheme.typography.subtitle2
        )
    }
}

@Composable
private fun CreateProfileImage(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .size(150.dp)
            .padding(4.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, color = Color.LightGray),
        elevation = 5.dp,
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.bussiness_man),
            contentDescription = "profile_image",
            modifier = modifier.size(150.dp),
            contentScale = ContentScale.Crop
        )
    }
}


//@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BizCardTheme {
        createBizCard()
    }
}