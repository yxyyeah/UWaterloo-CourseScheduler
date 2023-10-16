package screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontFamily
import navcontroller.NavController

@Composable
fun SchedulePage(
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        ExtendedFloatingActionButton(
            onClick = {
                navController.navigate(Screen.CourseSelectionPage.name)
            },
            icon = { Icon(Icons.Filled.Add, "Add Courses") },
            text = { Text(text = "Add Courses") },
        )
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        ExtendedFloatingActionButton(
            onClick = {
                navController.navigate(Screen.PreferenceSelectionPage.name)
            },
            icon = { Icon(Icons.Filled.Add, "Add Preferences") },
            text = { Text(text = "Add Preferences") },
        )
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        ExtendedFloatingActionButton(
            onClick = {
                navController.navigateBack()
            },
            icon = { Icon(Icons.Filled.Edit, "Go Back") },
            text = { Text(text = "Go Back") },
        )
    }
}
