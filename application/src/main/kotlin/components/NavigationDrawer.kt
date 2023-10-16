package components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import navcontroller.NavController
import style.*

@Composable
fun navDrawer(
    navController: NavController,
    content: @Composable () -> Unit
){
    // [START android_compose_layout_material_modal_drawer_programmatic]
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = false,
        drawerContent = {
            ModalDrawerSheet {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ){
                    Row(

                    ){  }
                    TextButton(
                        onClick = {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        }
                    ) {
                        Icon(Icons.Outlined.ArrowBack, contentDescription = "", tint =  md_theme_light_primary)
                    }

                }
                NavigationDrawerItem(
                    icon = { Icon(Icons.Outlined.CheckCircle, "") },
                    label = { Text(text = "New Schedule") },
                    selected = navController.currentScreen.value == Screen.WelcomePage.name,
                    onClick = {
                        navController.navigate(Screen.WelcomePage.name)
                        scope.launch {
                            drawerState.apply { close() }
                        }
                    }
                )
                NavigationDrawerItem(
                    icon = { Icon(Icons.Outlined.CheckCircle, "") },
                    label = { Text(text = "My Schedule (now goes to generate schedule page)") },
                    badge = { Text(text = "0") },
                    selected = navController.currentScreen.value == Screen.SchedulePage.name,
                    onClick = {
                        scope.launch {
                            drawerState.apply { close() }
                        }
                        navController.navigate(Screen.SchedulePage.name)
                    }
                )
            }
        },
    ) {
        Scaffold(
            topBar = {
                Box(
                    modifier = Modifier.fillMaxHeight().size(width = 50.dp, height = 0.dp).background(color = md_theme_light_surfaceVariant),
                ){
                    TextButton(
                        onClick = {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        }
                    ) {
                        Icon(Icons.Outlined.AccountCircle, contentDescription = "", tint =  md_theme_light_primary)
                    }
                }
            }
        ) { contentPadding ->
            // Screen content
            content()
            // [START_EXCLUDE silent]
            Box(modifier = Modifier.padding(contentPadding)) { /* ... */ }
            // [END_EXCLUDE]
        }
    }
}