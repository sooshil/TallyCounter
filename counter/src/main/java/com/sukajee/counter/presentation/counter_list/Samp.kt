package com.sukajee.counter.presentation.counter_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SampleScreen() {
    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        HeaderText("Hi!", 30.sp)
        HeaderText("Accounts", 12.sp)
        SubHeaderText("Deposits $ Investments")

        Spacer(modifier = Modifier.height(10.dp))

        AccountCard("FIFTH THIRD MOMENTUM CHECKING x2345", "$5000.24", "Available") { isSheetOpen = true }
        Spacer(modifier = Modifier.height(10.dp))
        AccountCard("FIFTH THIRD MOMENTUM SAVING x2345", "$5000.24", "Available") { isSheetOpen = true }
        Spacer(modifier = Modifier.height(10.dp))

        HeaderText("Credit Cards & loans", 14.sp)
        Spacer(modifier = Modifier.height(10.dp))
        AccountCard("Mastercard Account x2345", "$500.24", "Balance") { isSheetOpen = true }
        Spacer(modifier = Modifier.height(10.dp))

        HeaderText("Services", 14.sp)
        Spacer(modifier = Modifier.height(10.dp))
        ServiceCard()

        Spacer(modifier = Modifier.height(60.dp))
        NavigationBar()

        if (isSheetOpen) {
            BottomSheet(sheetState) { isSheetOpen = false }
        }
    }
}

@Composable
fun HeaderText(text: String, size: TextUnit) {
    Text(text = text, fontSize = size, fontWeight = FontWeight.Bold)
}

@Composable
fun SubHeaderText(text: String) {
    Text(text = text, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
}

@Composable
fun AccountCard(title: String, amount: String, subtitle: String, onMoreClick: () -> Unit) {
    Surface(
        shadowElevation = 10.dp,
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(text = title, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = amount,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xC60353F4)
                )
                IconButton(onClick = onMoreClick) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = null,
                        modifier = Modifier.size(30.dp),
                        tint = Color(0xC60337F4)
                    )
                }
            }
            SubHeaderText(subtitle)
        }
    }
}

@Composable
fun ServiceCard() {
    Surface(
        shadowElevation = 10.dp,
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text("Identify Alert", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color(0xC60353F4))
            Text("Premium", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color(0xC60347F4))
            Text("Take control of your credit and", fontSize = 15.sp, fontWeight = FontWeight.Bold)
            Text("personal information.", fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
    }
}

data class NavItem(
    val icon: ImageVector,
    val label: String,
    val color: Color
)

@Composable
fun NavigationBar() {
    val icons = listOf(
        NavItem(Icons.Default.Home, "Home", Color(0xC60353F4)),
        NavItem(Icons.Default.ShoppingCart, "Cart", Color.Gray),
        NavItem(Icons.Default.CheckCircle, "Check", Color.Gray),
        NavItem(Icons.Default.DateRange, "Goals", Color.Gray)
    )

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        icons.forEach { (icon, text, color) ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(icon, null, Modifier.size(if (icon == Icons.Default.DateRange) 45.dp else 40.dp), color)
                Text(text, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = color)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(sheetState: SheetState, onDismiss: () -> Unit) {
    ModalBottomSheet(sheetState = sheetState, onDismissRequest = onDismiss) {
        Column(modifier = Modifier.padding(10.dp)) {
            Surface(shadowElevation = 10.dp, shape = RoundedCornerShape(12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("FIFTH THIRD MOMENTUM CHECKING x2345", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    Icon(Icons.Default.Close, null, tint = Color(0xC60337F4))
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            listOf("Transfer Funds", "Send Money with Zelle@", "Manage Card", "View Documents").forEach { text ->
                Surface(shadowElevation = 10.dp, shape = RoundedCornerShape(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.AddCircle, null, tint = Color(0xC60337F4))
                        Spacer(Modifier.width(16.dp))
                        Text(text, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xC60337F4))
                        Spacer(Modifier.weight(1f))
                        Icon(Icons.Default.KeyboardArrowRight, null, Modifier.size(40.dp), Color(0xC60337F4))
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}