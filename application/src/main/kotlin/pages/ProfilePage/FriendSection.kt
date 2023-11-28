package pages.ProfilePage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import friendSearchInputField
import logic.friends.*
import org.jetbrains.skia.Color
import style.md_theme_light_inversePrimary
import style.md_theme_light_primaryContainer

/*
var allUser = listOf(
    "apple",
    "beta",
    "cookie",
    "dean",
    "elephant",
    "fire",
    "goose",
    "horse",
    "italy",
    "jiggle",
    "kitty",
    "lemma",
    "moose",
    "nonono",
    "opera",
    "pig"
)
*/

var allUser : MutableList<Pair<Int, String>> = mutableListOf()

@Composable
fun friendSection( USER_ID : Int) {
    val friendsList = remember { mutableStateOf(fetchFriendList(USER_ID)) }
    //val friendRequestList = remember { mutableListOf<Pair<Int, String>>() }
    val friendRequestList = remember { mutableStateOf(fetchFriendRequests(USER_ID)) }
    var ifFriendList by remember { mutableStateOf(true)}
    var ifMessages by remember { mutableStateOf(false)}
    var ifAddFriends by remember { mutableStateOf(false)}
    /*val friendsList =
        remember {
            mutableStateListOf<String>(
                "queen",
                "room",
                "snake",
                "tea",
                "umbrella",
                "vehicle",
                "watermelon",
                "banana",
                "canana",
                "danana",
                "panana",
                "ananana"
            )
        }*/
    Column (
    )
    {
        Text(text = "My Friends", fontSize = 30.sp)
        Row(
            modifier = Modifier.padding(top = 10.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (ifFriendList) {
                OutlinedButton(
                    content = { Text("View and Add") },
                    onClick = {
                        friendsList.value = fetchFriendList(USER_ID)
                        ifFriendList = true
                        ifMessages = false
                        ifAddFriends = false
                    },
                    modifier = Modifier.size(180.dp, 40.dp)
                )
            } else {
                TextButton(
                    content = { Text("View and Add") },
                    onClick = {
                        friendsList.value = fetchFriendList(USER_ID)
                        ifFriendList = true
                        ifMessages = false
                        ifAddFriends = false
                    },
                    modifier = Modifier.size(180.dp, 40.dp)
                )
            }
            if (ifMessages) {
                OutlinedButton(
                    content = { Text("Friend Requests") },
                    onClick = {
                        friendRequestList.value = fetchFriendRequests(USER_ID)
                        ifFriendList = false
                        ifMessages = true
                        ifAddFriends = false
                    },
                    modifier = Modifier.size(180.dp, 40.dp).padding(start = 5.dp)
                )
            } else {
                TextButton(
                    content = { Text("Friend Requests") },
                    onClick = {
                        friendRequestList.value = fetchFriendRequests(USER_ID)
                        ifFriendList = false
                        ifMessages = true
                        ifAddFriends = false
                    },
                    modifier = Modifier.size(180.dp, 40.dp).padding(start = 5.dp)
                )
            }
            /*if (ifAddFriends) {
                OutlinedButton(
                    content = { Text("Add Friends") },
                    onClick = {
                        ifFriendList = false
                        ifMessages = false
                        ifAddFriends = true
                    },
                    modifier = Modifier.size(140.dp, 40.dp)
                )
            } else {
                TextButton(
                    content = { Text("Add Friends") },
                    onClick = {
                        ifFriendList = false
                        ifMessages = false
                        ifAddFriends = true
                    },
                    modifier = Modifier.size(140.dp, 40.dp)
                )
            }*/
        }
        Box(
            modifier = Modifier.height(650.dp).fillMaxWidth().padding(top = 10.dp),
        ) {
            if (ifFriendList) {
                Column(
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        "Add Friends:",
                        modifier = Modifier,
                        fontSize = 12.sp,
                        fontStyle = FontStyle.Italic
                    )
                    friendSearchInputField(allUser, {}, USER_ID)
                    Text(
                        "My Friend List:",
                        modifier = Modifier.padding(top = 10.dp, bottom = 5.dp),
                        fontSize = 12.sp,
                        fontStyle = FontStyle.Italic
                    )
                    ElevatedCard(
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp
                        ),
                    ) {
                        LazyColumn(
                            modifier = Modifier,
                        ) {
                            items(friendsList.value, { it }) { friend ->
                                ListItem(
                                    modifier = Modifier
                                        .height(50.dp).fillMaxWidth(),
                                    headlineContent = { Text(friend.second) },
                                    trailingContent = {
                                        Row() {
                                            TextButton(
                                                onClick = { fetchFriendProfile(USER_ID, friend.first) },
                                                modifier = Modifier.padding(end = 4.dp)
                                            ) {
                                                Text("View profile")
                                            }
                                            TextButton(
                                                onClick = { },
                                                modifier = Modifier
                                            ) {
                                                Text("Remove")
                                            }
                                        }
                                    },
                                    tonalElevation = 30.dp,
                                    colors = ListItemDefaults.colors(containerColor = md_theme_light_primaryContainer)
                                )
                                Divider(thickness = 1.dp, color = md_theme_light_inversePrimary)
                            }
                        }
                    }
                }
            } else if (ifMessages) {
                if (friendRequestList.value.isEmpty()) {
                    Text("You currently have no invitation",
                            fontStyle = FontStyle.Italic)
                }
                ElevatedCard(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    ),
                    modifier = Modifier,
                ) {
                    LazyColumn(
                        modifier = Modifier,
                    ) {
                        items(friendRequestList.value, { it }) { user ->
                            ListItem(
                                modifier = Modifier
                                    .height(70.dp).fillMaxWidth(),
                                headlineContent = { Text(user.second) },
                                trailingContent = {
                                    Row(

                                    ) {
                                        OutlinedButton(
                                            onClick = {
                                                approveFriendRequest(USER_ID, user.first)
                                                //friendRequestList.clear()
                                                //friendRequestList.addAll(fetchFriendRequests(USER_ID))
                                                friendRequestList.value = fetchFriendRequests(USER_ID)
                                            },
                                            modifier = Modifier.padding(5.dp)
                                        ) {
                                            Text("Accept")
                                        }
                                        OutlinedButton(
                                            onClick = { denyFriendRequest(USER_ID, user.first) },
                                            modifier = Modifier.padding(5.dp),
                                        ) {
                                            Text("Decline")
                                        }
                                    }
                                },
                                tonalElevation = 30.dp,
                                colors = ListItemDefaults.colors(containerColor = md_theme_light_primaryContainer)
                            )
                            Divider(thickness = 1.dp, color = md_theme_light_inversePrimary)
                        }
                    }
                }
            } else if (ifAddFriends) {
                friendSearchInputField(allUser, {}, USER_ID)
            }
        }
    }
}