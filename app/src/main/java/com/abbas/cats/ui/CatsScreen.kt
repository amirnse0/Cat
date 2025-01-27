package com.abbas.cats.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.request.ImageRequest
import coil3.compose.AsyncImage
import coil3.request.crossfade
import com.abbas.cats.R
import com.abbas.cats.usecase.presentationmodel.Cat

@Composable
fun CatsScreen(modifier: Modifier = Modifier) {
}

@Composable
fun CatsLazyColumn(modifier: Modifier = Modifier, cats: List<Cat>, listState: LazyListState, onItemClick: () -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        state = listState
    ) {
        items(cats, key = {cat -> cat.id }) { cat ->
            CatItemCard(cat = cat) {
                onItemClick()
            }
        }
    }
}

@Composable
fun CatItemCard(modifier: Modifier = Modifier, cat: Cat, onItemClick: () -> Unit) {
    Card(
        modifier = modifier
            .padding(10.dp)
            .shadow(8.dp, shape = RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(16.dp),
        onClick = { onItemClick() }
    ) {
        CatPicture(image = cat.image)
        Text(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(10.dp),
            text = cat.name
        )
    }
}

@Composable
fun CatPicture(modifier: Modifier = Modifier, image: String) {
    val context = LocalContext.current

    AsyncImage(
        modifier = modifier
            .fillMaxWidth()
            .size(200.dp),
        model = ImageRequest.Builder(context)
            .data(image)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.placeholder),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}