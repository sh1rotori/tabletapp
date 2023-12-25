package com.example.myapplication22222

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel


@Composable
fun SkillScreen() {
    val scrollState = rememberScrollState()
    val skillsData = getSkillsDataFromNetwork()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        SkillTitle("Skills List")

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(skillsData) { skillType ->
                SkillType(skillType)
            }
        }
    }
}


@Composable
fun SkillTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Composable
fun SkillType(skillType: SkillTypeData) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Title: Skill Type Name
        Text(
            text = skillType.name,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // List of skills for the current skill type
        Column {
            skillType.skills.forEach { (skillId, skillName) ->
                SkillItem(skillId, skillName)
            }
        }
    }
}

@Composable
fun SkillItem(skillId: String, skillName: String) {
    var showDialog by remember { mutableStateOf(false) }

    // When the skill item is clicked, show the dialog
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { showDialog = true }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Skill Name
        Text(
            text = skillName,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        )

        // Arrow icon
        Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
    }

    // Display the skill dialog when showDialog is true
    if (showDialog) {
        SkillDialog(skillId = skillId, onDismiss = { showDialog = false })
    }
}

@Composable
fun SkillDialog(skillId: String, onDismiss: () -> Unit) {
    val skillDetails = getSkillDetailsFromNetwork(skillId)

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(text = skillDetails.name, style = MaterialTheme.typography.titleSmall)
        },
        text = {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(8.dp) // Add padding to the column
            ) {
                Text(text = skillDetails.introduction, style = MaterialTheme.typography.titleMedium)
            }
        },
        confirmButton = {
            Button(
                onClick = { onDismiss() },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(text = "Далее")
            }
        }
    )
}

// Simulated data retrieval functions (replace with actual network requests)
fun getSkillsDataFromNetwork(): List<SkillTypeData> {
    // Simulated data (replace with actual network request)
    return listOf(
        SkillTypeData(
            skillTypeId = 1,
            name = "Manufacturing and Engineering Technology",
            skills = mapOf(
                "0000" to "Industrial Mechanics",
                "0001" to "Manufacturing Team Challenge",
                "0002" to "Mechatronics",
                "0003" to "Mechanical Engineering CAD",
                "0004" to "CNC Turning",
                "0005" to "CNC Milling"
            )
        ),
        SkillTypeData(
            skillTypeId = 2,
            name = "Information and Communication Technology",
            skills = mapOf(
                "1000" to "Information Network Cabling",
                "1001" to "IT Software Solutions for Business",
                "1002" to "Mobile Applications Development",
                "1003" to "Web Technologies"
            )
        )
    )
}

data class SkillTypeData(
    val skillTypeId: Int,
    val name: String,
    val skills: Map<String, String>
)

data class SkillDetails(
    val name: String,
    val introduction: String,
    val imgPath: String
)

// Simulated data retrieval functions (replace with actual network requests)
fun getSkillDetailsFromNetwork(skillId: String): SkillDetails {
    // Simulated data (replace with actual network request)
    return SkillDetails(
        name = "Mechanical Engineering CAD",
        introduction = "Computer aided design (CAD) uses specialist software to create two and three dimensional images and animations of projects both in manufacturing and for use in advertising and technical manuals. CAD can convey many types of information, including dimensions, types of material, and tolerances and is essential in offering solutions to both engineering and manufacturing problems. By producing photorealistic animations and videos, it can simulate how a design will actually function in the real world.",
        imgPath = "../skills_images/0003.jpeg"
    )
}