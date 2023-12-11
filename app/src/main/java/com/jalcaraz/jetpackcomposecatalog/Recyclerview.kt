package com.jalcaraz.jetpackcomposecatalog

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jalcaraz.jetpackcomposecatalog.Model.Superhero
import kotlinx.coroutines.launch

@Composable
fun SimpleRecyclerView() {

    val myList = listOf("Jesus", "Aris", "Pepe", "Manolo", "Jaime")
    LazyColumn {
        item { Text(text = "Header") }
        items(25) {
            Text(text = "Este es el item $it")
        }
        items(myList) {
            Text(text = "Hola me llamo $it")
        }
        item { Text(text = "Footer") }
    }
}

@Composable
fun SuperHeroView() {
//    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
//        items(getSuperheroes()) {
//            ItemHero(superhero = it)
//        }
//    }

    val context = LocalContext.current
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(getSuperheroes()) { superhero ->
            ItemHero(superhero = superhero)
            {
                Toast.makeText(context, superhero.superheroName, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SuperHeroStickyView() {

    val context = LocalContext.current
    val superhero: Map<String, List<Superhero>> = getSuperheroes().groupBy { it.publisher }

    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {

        superhero.forEach { (publisher, mySuperHero) ->

            stickyHeader {
                Text(
                    text = publisher,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Green),
                    fontSize = 16.sp,
                    color = Color.White
                )
            }

            items(mySuperHero) { superhero ->
                ItemHero(superhero = superhero)
                {
                    Toast.makeText(context, superhero.superheroName, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

@Composable
fun SuperHeroGridView() {

    val context = LocalContext.current
    LazyVerticalGrid(columns = GridCells.Fixed(2), content = {
        this.items(getSuperheroes()) { superhero ->
            ItemHero(superhero = superhero)
            {
                Toast.makeText(context, superhero.superheroName, Toast.LENGTH_SHORT).show()
            }
        }
    }, contentPadding = PaddingValues(8.dp))
}

@Composable
fun SuperHeroSpecialControlsView() {
    val context = LocalContext.current
    val rvState = rememberLazyListState()
    val coroutinesScope = rememberCoroutineScope()
    Column(modifier = Modifier.fillMaxWidth()) {

        LazyColumn(
            state = rvState,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(getSuperheroes()) { superhero ->
                ItemHero(superhero = superhero)
                {
                    Toast.makeText(context, superhero.superheroName, Toast.LENGTH_SHORT).show()
                }
            }
        }

        val showButton by remember {
            derivedStateOf { rvState.firstVisibleItemIndex > 0 }
        }
        if (showButton) {
            Button(
                onClick = {
                    coroutinesScope.launch {
                        rvState.animateScrollToItem(0)
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            ) {
                Text(text = "Soy un botón cool")
            }
        }
    }
}

@Composable
fun ItemHero(superhero: Superhero, onItemSelected: (Superhero) -> Unit) {
    Card(
        //border = BorderStroke(2.dp, Color.Red),
        elevation = CardDefaults.cardElevation(5.dp),
        modifier = Modifier
            //.width(200.dp)
            .fillMaxWidth()
            .clickable { onItemSelected(superhero) }) {
        Column {
            Image(
                painter = painterResource(id = superhero.photo),
                contentDescription = "Avatar",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = superhero.superheroName,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = superhero.realName,
                modifier = Modifier.align(Alignment.CenterHorizontally), fontSize = 12.sp
            )
            Text(
                text = superhero.publisher,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(8.dp), fontSize = 10.sp
            )
        }
    }
}

fun getSuperheroes(): List<Superhero> {
    return listOf(
        Superhero("Spiderman", "Peter Parker", "Marvel", R.drawable.spiderman),
        Superhero("Wolverine", "James Howlett", "Marvel", R.drawable.logan),
        Superhero("Batman", "Bruce Wayne", "DC", R.drawable.batman),
        Superhero("Thor", "Thor Odison", "Marvel", R.drawable.thor),
        Superhero("Flash", "Jay Garrick", "DC", R.drawable.flash),
        Superhero("Green Lantern", "Alan Scott", "DC", R.drawable.green_lantern),
        Superhero("Wonder Woman", "Princess Diana", "DC", R.drawable.wonder_woman)
    )
}