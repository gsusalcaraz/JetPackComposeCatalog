package com.jalcaraz.jetpackcomposecatalog

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jalcaraz.jetpackcomposecatalog.ui.theme.JetPackComposeCatalogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackComposeCatalogTheme {
                // A surface container using the 'background' color from the theme
                var selected by remember {
                    mutableStateOf("Ejemplo 1")
                }
                Surface(
                    // modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val myOptions = getOptions(listOf("Jesus", "Ejemplo", "Pikachu"))
                    Column {

                        // var myText by remember { mutableStateOf("Jesus") }
                        // MyTextField(myText) { myText = it }

                        // MyTristatusCheckBox()

                        //myOptions.forEach {
                        //   MyCheckBoxWithTextCompleted(it)
                        //}

                        // MyRadioButtonList(selected) { selected = it }

/*
                        Column {
                            var show by remember { mutableStateOf(false) }

                            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                Button(onClick = { show = true }) {
                                    Text(text = "Mostrar diálogo")
                                }
                            }
                            MyAlertDialog(
                                show = show,
                                onDismiss = { show = false },
                                onConfirm = { Log.i("Gsus", "Click") })
                        }
 */
/*
                        Column {

                            var show by remember { mutableStateOf(false) }

                            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                Button(onClick = { show = true }) {
                                    Text(text = "Mostrar diálogo")
                                }
                            }
                            MySimpleCustomDialog(
                                show = show,
                                onDismiss = { show = false })
                        }


 */
                        /*
                         Column {

                            var show by remember { mutableStateOf(false) }

                            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                Button(onClick = { show = true }) {
                                    Text(text = "Mostrar diálogo")
                                }
                            }
                             MyCustomDialog(
                                show = show,
                                onDismiss = { show = false })
                        }

                         */
                        Column {

                            var show by remember { mutableStateOf(false) }

                            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                Button(onClick = { show = true }) {
                                    Text(text = "Mostrar diálogo")
                                }
                            }
                            MyConfirmationDialog(
                                show = show,
                                onDismiss = { show = false })
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetPackComposeCatalogTheme {
        MyDropDownMenuM3()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDropDownMenuM3() {
    val desserts = listOf("Helado", "Chocolate", "Café", "Natillas", "Fruta")
    var selectedText by remember { mutableStateOf(desserts[0]) }
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            modifier = Modifier
                .menuAnchor(),
            readOnly = true,
            value = selectedText,
            onValueChange = {},
            label = { Text("Desserts") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
        ) {
            desserts.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption) },
                    onClick = {
                        selectedText = selectionOption
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}

@Composable
fun MyDropDownMenu() {

    var selectedText by remember { mutableStateOf("") }
    var expander by remember { mutableStateOf(false) }
    val desserts = listOf("Helado", "Chocolate", "Café", "Natillas", "Fruta")

    Column(Modifier.padding(20.dp)) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .clickable { }
                .fillMaxWidth()
        )
        DropdownMenu(
            expanded = expander,
            onDismissRequest = { expander = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            desserts.forEach {
                DropdownMenuItem(text = { desserts }, onClick = { expander = false })
            }
        }
    }
}

@Composable
fun MyDivider() {
    Divider(
        Modifier
            .fillMaxWidth()
            .padding(top = 16.dp), color = Color.Red
    )
}

/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBadgeBox(){
    BadgedBox() {
        Icon(imageVector = Icons.Default.Star, contentDescription = "")    }
}

 */

@Composable
fun MyCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Red),
        border = BorderStroke(5.dp, Color.Yellow)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(text = "Ejemplo 1", color = Color.White)
            Text(text = "Ejemplo 2", color = Color.Yellow)
            Text(text = "Ejemplo 3", color = Color.Green)
            Text(text = "Ejemplo 4", color = Color.White)
        }
    }
}

@Composable
fun MyRadioButtonList(name: String, onItemSelected: (String) -> Unit) {

    Column(Modifier.fillMaxWidth()) {
        Row(Modifier.fillMaxWidth()) {
            RadioButton(selected = name == "Ejemplo 1", onClick = { onItemSelected("Ejemplo 1") })
            Text(modifier = Modifier.padding(top = 12.dp), text = "Ejemplo 1")
        }
        Row(Modifier.fillMaxWidth()) {
            RadioButton(selected = name == "Ejemplo 2", onClick = { onItemSelected("Ejemplo 2") })
            Text(modifier = Modifier.padding(top = 12.dp), text = "Ejemplo 2")
        }
        Row(Modifier.fillMaxWidth()) {
            RadioButton(selected = name == "Ejemplo 3", onClick = { onItemSelected("Ejemplo 3") })
            Text(modifier = Modifier.padding(top = 12.dp), text = "Ejemplo 3")
        }
        Row(Modifier.fillMaxWidth()) {
            RadioButton(selected = name == "Ejemplo 4", onClick = { onItemSelected("Ejemplo 4") })
            Text(modifier = Modifier.padding(top = 12.dp), text = "Ejemplo 4")
        }
    }
}

@Composable
fun MyRadioButton() {
    Row(Modifier.fillMaxWidth()) {
        RadioButton(
            selected = false, onClick = { }, enabled = false, colors = RadioButtonDefaults.colors(
                selectedColor = Color.Red,
                unselectedColor = Color.Yellow,
                disabledSelectedColor = Color.Green
            )
        )
        Text(modifier = Modifier.padding(top = 12.dp), text = "Ejemplo 1")
    }

}

@Composable
fun MyTristatusCheckBox() {
    var status by rememberSaveable { mutableStateOf(ToggleableState.Off) }

    TriStateCheckbox(state = status, onClick = {
        status = when (status) {
            ToggleableState.On -> {
                ToggleableState.Off

            }

            ToggleableState.Off -> ToggleableState.Indeterminate
            ToggleableState.Indeterminate -> ToggleableState.On
        }
    })
}

@Composable
fun getOptions(titles: List<String>): List<CheckInfo> {
    return titles.map {
        var status by rememberSaveable {
            mutableStateOf(false)
        }
        CheckInfo(
            title = it,
            selected = status,
            onCheckedChange = { myNewStatus ->
                status = myNewStatus
            }
        )
    }
}

@Composable
fun MyCheckBoxWithTextCompleted(checkInfo: CheckInfo) {

    Row(Modifier.padding(8.dp)) {
        Checkbox(
            checked = checkInfo.selected,
            onCheckedChange = { checkInfo.onCheckedChange(!checkInfo.selected) })
        Spacer(modifier = Modifier.width(8.dp))
        Text(modifier = Modifier.padding(top = 12.dp), text = checkInfo.title)
    }
}

@Composable
fun MyCheckBoxWithText() {
    var state by rememberSaveable {
        mutableStateOf(true)
    }

    Row(Modifier.padding(8.dp)) {
        Checkbox(checked = state, onCheckedChange = { state = !state })
        Spacer(modifier = Modifier.width(8.dp))
        Text(modifier = Modifier.padding(top = 12.dp), text = "Ejemplo 1")
    }
}

@Composable
fun MyCheckBox() {
    var state by rememberSaveable {
        mutableStateOf(true)
    }
    Checkbox(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize(),
        checked = state,
        onCheckedChange = { state = !state },
        enabled = true,
        colors = CheckboxDefaults.colors(
            checkedColor = Color.Yellow,
            uncheckedColor = Color.Blue,
            checkmarkColor = Color.Black
        )
    )
}

@Composable
fun MySwitch() {

    var state by rememberSaveable {
        mutableStateOf(true)
    }

    Switch(
        checked = state,
        onCheckedChange = { state = !state },
        enabled = true,
        colors = SwitchDefaults.colors(
            checkedThumbColor = Color.Green,
            checkedTrackColor = Color.Magenta,
            uncheckedThumbColor = Color.Red,
            uncheckedTrackColor = Color.Cyan
        )
    )
}

@Composable
fun MyProgressAdvance() {

    var progreso by rememberSaveable { mutableFloatStateOf(0f) }

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LinearProgressIndicator(progress = progreso)

        Row(
            Modifier
                .fillMaxWidth()
                .padding(25.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                if (progreso < 1f) {
                    progreso += 0.1f
                }
            }) {
                Text(text = "+")
            }
            Button(onClick = {
                if (progreso > 0f) {
                    progreso -= 0.1f
                }
            }) {
                Text(text = "-")
            }
        }
    }
}

@Composable
fun MyProgress() {
    var showLoading by rememberSaveable { mutableStateOf(false) }

    Column(
        Modifier
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (showLoading) {
            CircularProgressIndicator(
                color = Color.Red,
                strokeWidth = 5.dp,
                trackColor = Color.Green
            )
            LinearProgressIndicator(
                modifier = Modifier.padding(32.dp),
                color = Color.Red,
                trackColor = Color.Blue
            )
        }
        Button(onClick = { showLoading = !showLoading }) {
            Text(text = "Cargar perfil")
        }

    }
}

@Composable
fun MyIcon() {
    Icon(imageVector = Icons.Rounded.AddCircle, contentDescription = "Icono", tint = Color.Red)
}

@Composable
fun MyImageAdvance() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "ejemplo",
        modifier = Modifier
            .clip(CircleShape)
            .border(5.dp, Color.Red, CircleShape)
    )
}

@Composable
fun MyImage() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "ejemplo",
        alpha = 0.5f
    )
}

@Composable
fun MyButtonExample() {
    var enabled by rememberSaveable { mutableStateOf(true) }
    Column(
        Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Button(
            onClick = { enabled = false },
            enabled = enabled,
            colors = ButtonDefaults.buttonColors(
                Color.Red,
                contentColor = Color.Green
            ),
            border = BorderStroke(5.dp, Color.Green)
        )
        {
            Text(text = "Hola")
        }

        OutlinedButton(
            onClick = { enabled = false },
            enabled = enabled,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Hola")
        }

        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Hola")
        }
    }
}

@Composable
fun MyTextFieldOutlined() {
    var myText by remember { mutableStateOf("") }

    OutlinedTextField(
        value = myText,
        onValueChange = { myText = it },
        modifier = Modifier.padding(24.dp),
        label = { Text(text = "Holita") },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Magenta,
            unfocusedIndicatorColor = Color.Red
        )
    )
}

@Composable
fun MyTextFieldAdvance() {
    var myText by remember { mutableStateOf("") }

    TextField(
        value = myText,
        onValueChange = {
            myText =
                if (it.contains("a")) {
                    it.replace("a", "")
                } else {
                    it
                }
        },
        label = { Text(text = "Introduce tu nombre") })

}

@Composable
fun MyTextField(name: String, onValueChanged: (String) -> Unit) {

    TextField(value = name, onValueChange = { onValueChanged(it) })
}

@Composable
fun MyText() {
    Column(Modifier.fillMaxSize()) {
        Text(text = "Esto es un ejemplo")
        Text(text = "Esto es un ejemplo", color = Color.Blue)
        Text(text = "Esto es un ejemplo", fontWeight = FontWeight.Bold)
        Text(text = "Esto es un ejemplo", style = TextStyle(fontFamily = FontFamily.Cursive))
        Text(text = "Es lo mismo que lo de arriba", fontFamily = FontFamily.Cursive)
        Text(
            text = "Esto es un ejemplo",
            style = TextStyle(textDecoration = TextDecoration.LineThrough)
        )
        Text(
            text = "Esto es un ejemplo",
            style = TextStyle(
                textDecoration = TextDecoration.combine(
                    listOf(
                        TextDecoration.Underline,
                        TextDecoration.LineThrough
                    )
                )
            )
        )
        Text(text = "Esto es un ejemplo", fontSize = 30.sp)
    }
}