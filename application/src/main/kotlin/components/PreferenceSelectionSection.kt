package components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ListItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import components.InputPreferences.MaxHoursPerDayChip
import components.InputPreferences.NonPreferredTimeChip
import components.InputPreferences.PreferredTimeChip
import logic.preference.Preference
import org.burnoutcrew.reorderable.*

@Composable
fun preferenceSelectionSection(
    preferences: MutableList<Preference>,
    showCallBack: () -> Unit,
    changeCallBack: (from : Int , to : Int) -> Unit,
    deleteCallBack: (preference: Preference) -> Unit
) {
    val state = rememberReorderableLazyListState(onMove = { from, to ->
        changeCallBack(from.index, to.index)
    })
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        ExtendedFloatingActionButton(
            onClick = { showCallBack() },
            icon = { Icon(Icons.Filled.Add, "Add Preferences") },
            text = { Text(text = "Add Preferences") },
            modifier = Modifier
                .size(width = 200.dp, height = 56.dp)
        )
        Text(
            text = "Drag and drop a selected preference to modify its weighting:",
            fontSize = 12.sp,
            fontStyle = FontStyle.Italic
        )
        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            modifier = Modifier.size(width = 446.dp, height = 450.dp),
            shape = RoundedCornerShape(0.dp)
        ) {
            LazyColumn(
                state = state.listState,
                modifier = Modifier
                    .reorderable(state)
                    .detectReorder(state)
            ) {
                items(preferences, { it }) { preference ->
                    ReorderableItem(state, key = preference) { isDragging ->
                        val elevation = animateDpAsState(if (isDragging) 16.dp else 0.dp)
                        ListItem(
                            modifier = Modifier
                                .shadow(elevation.value)
                                .size(width = 446.dp, height = 70.dp)
                                .clip(shape = RoundedCornerShape(30.dp)),
                            headlineContent = { Text(preference.toString()) },
                            trailingContent = { TextButton(
                                onClick = {deleteCallBack(preference)}
                            ){
                                Icon(Icons.Outlined.Close, "")
                            } },
                            tonalElevation = 30.dp
                        )
                        Divider()
                    }
                }
            }
        }
    }
}
@Composable
fun preferenceDialog(
    showAddPreference : MutableState<Boolean>,
    addCallBack: (String, List<String>) -> Unit
) {
    val preferenceDialogSection = listOf("Time", "Courses", "Instructors", "Friends")
    if(showAddPreference.value) {
        Dialog(
            onDismissRequest = { showAddPreference.value = false },
            properties = DialogProperties(dismissOnClickOutside = true, usePlatformDefaultWidth = false)
        ) {
            Card(
                modifier = Modifier
                    .size(700.dp, 1000.dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        . padding(20.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start,
                ) {
                    for(sectionName in preferenceDialogSection) {
                        Text(sectionName,
                            fontSize = 30.sp,
                            modifier = Modifier
                                .padding(top = 20.dp)
                        )
                        Divider(thickness = 2.dp)
                        if (sectionName == "Time") {
                            MaxHoursPerDayChip(addCallBack)
                            Spacer(modifier = Modifier.height(10.dp))
                            PreferredTimeChip(addCallBack)
                            Spacer(modifier = Modifier.height(10.dp))
                            NonPreferredTimeChip(addCallBack)
                        }
                        else if (sectionName == "Courses") {
                            ////////FILLMEIN////////
                        }
                        else if (sectionName == "Instructors") {
                            ////////FILLMEIN////////
                        }
                        else if (sectionName == "Friends") {
                            ////////FILLMEIN////////
                        }
                    }
                    TextButton(
                        onClick = { showAddPreference.value = false },
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Text("Close")
                    }
                }
            }
        }
    }
}

