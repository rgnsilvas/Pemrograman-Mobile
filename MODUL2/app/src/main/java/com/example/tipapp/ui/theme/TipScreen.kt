package com.example.tipapp.ui.theme

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TipScreen(
    modifier: Modifier = Modifier,
    viewModel: TipViewModel = viewModel()
) {
    val context = LocalContext.current

    val amountInput by viewModel.amountInput.collectAsState()
    val tipPercent by viewModel.tipPercent.collectAsState()
    val roundUp by viewModel.roundUp.collectAsState()

    var showResult by rememberSaveable { mutableStateOf(false) }
    val tip = viewModel.calculateTip()

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = amountInput,
            onValueChange = { viewModel.onAmountChange(it) },
            label = { Text("Cost of Service") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Text("How was the service?")

        val options = listOf(
            0.20 to "Amazing (20%)",
            0.18 to "Good (18%)",
            0.15 to "Okay (15%)"
        )

        options.forEach { (value, label) ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = tipPercent == value,
                        onClick = { viewModel.onTipChange(value) }
                    )
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = tipPercent == value,
                    onClick = { viewModel.onTipChange(value) }
                )
                Text(label)
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Round up tip?")
            Spacer(modifier = Modifier.width(8.dp))
            Switch(
                checked = roundUp,
                onCheckedChange = { viewModel.onRoundUpChange(it) }
            )
        }

        Button(
            onClick = {
                showResult = amountInput.isNotBlank() && amountInput.toDoubleOrNull() != null
                if (!showResult) {
                    Toast.makeText(context, "Please enter a valid amount", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("CALCULATE", color = MaterialTheme.colorScheme.onPrimary)
        }

        if (showResult) {
            Text(
                text = "Tip Amount: $%.2f".format(tip),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}
