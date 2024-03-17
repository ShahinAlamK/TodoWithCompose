package com.example.todo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.todo.R

@Composable
fun TodoCard(delete:()-> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(MaterialTheme.shapes.small)
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(35.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "01", style = MaterialTheme.typography.bodyLarge)
            }
            Spacer(modifier = Modifier.width(15.dp))

            Column(
                modifier = Modifier.weight(.5f)
            ) {
                Text(
                    text = "Bangladesh Sri Lanka ODI Match 2024",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "short description", style = MaterialTheme.typography.labelSmall)
            }

            Spacer(modifier = Modifier.width(15.dp))

            RoundIcon(
                color = MaterialTheme.colorScheme.error,
                icon = {
                    Icon(
                        tint = MaterialTheme.colorScheme.error ,
                        painter = painterResource(id = R.drawable.ic_delete),
                        contentDescription = ""
                    )
                },
                onClick = { delete() }
            )
        }

        Text(
            modifier = Modifier
                .padding(bottom = 5.dp)
                .align(Alignment.BottomEnd),
            text = "Fri/ 15.03.2023", style = MaterialTheme.typography.labelSmall
        )
    }
}