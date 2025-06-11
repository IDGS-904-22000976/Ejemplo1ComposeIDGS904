package com.example.ejemplo1composeidgs904

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ejemplo1composeidgs904.ui.theme.Ejemplo1ComposeIDGS904Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Ejemplo1ComposeIDGS904Theme {
                Column(modifier = Modifier.fillMaxSize()) {
                    Tarjeta(personajes)
                }
            }
        }
    }
}

data class PersonajeTarjeta(
    val nombre: String,
    val descripcion: String
)

private val personajes: List<PersonajeTarjeta> = listOf(
    PersonajeTarjeta("gohan", "Es un humano con un machete dentro de su mochila"),
    PersonajeTarjeta("vegeta", "El príncipe de los Saiyajin, siempre buscando superar a Goku."),
    PersonajeTarjeta("piccolo", "Un Namekiano sabio y fuerte, mentor de Gohan."),
    PersonajeTarjeta("goku", "El guerrero más fuerte de la Tierra, amante de la comida."),
    PersonajeTarjeta("freezer", "Emperador del mal, enemigo jurado de los Saiyajin."),
    PersonajeTarjeta("cell", "Bio-androide perfecto, creado para ser el guerrero definitivo y darte consejos sobre como ponerte vrgas."),
    PersonajeTarjeta("buu", "Una criatura mágica con múltiples transformaciones."),
    PersonajeTarjeta("trunks", "Hijo de Vegeta y Bulma, un prodigio en el combate."),
    PersonajeTarjeta("krillin", "El mejor amigo de Goku, a pesar de ser más débil."),
    PersonajeTarjeta("Dodoria", "Secuaz de freezer y en su raza es una mujer."),

)

@Composable
fun Tarjeta(personajes: List<PersonajeTarjeta>) {
    LazyColumn {
        items(personajes) { personaje ->
            MyPersonajes(personaje)
        }
    }
}

@Composable
fun MyPersonajes(personaje: PersonajeTarjeta) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            ImagenHeroe(personaje.nombre.lowercase())
            // Aquí llamamos a la nueva función DetallesPersonaje
            DetallesPersonaje(personaje)
        }
    }
}

// Nueva función para mostrar los detalles del personaje
@Composable
fun DetallesPersonaje(personaje: PersonajeTarjeta) {
    Column(modifier = Modifier.padding(start = 8.dp)) {
       Personaje(personaje.nombre,MaterialTheme.colorScheme.primary,MaterialTheme.typography.titleLarge)
        Personaje(personaje.descripcion,MaterialTheme.colorScheme.onBackground,MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun Personaje(name: String, color: Color,style: TextStyle){
    Text(text = name)
}

@Composable
fun ImagenHeroe(imageName : String) {
    val context= LocalContext.current
    val ImageResid = remember(imageName) {
        context.resources.getIdentifier(imageName.lowercase(),"drawable",context.packageName)
    }
    Image(
        painter = painterResource(id = ImageResid),
        contentDescription = "Imagen del personaje",
        modifier = Modifier
            .padding(4.dp)
            .size(80.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewMessageCard() {
    Ejemplo1ComposeIDGS904Theme {
        Tarjeta(personajes)
    }
}