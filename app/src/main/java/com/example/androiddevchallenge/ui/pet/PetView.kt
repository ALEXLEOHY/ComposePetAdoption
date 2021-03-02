/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.pet

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.Pet
import com.example.androiddevchallenge.model.UiState
import com.example.androiddevchallenge.ui.view.FullScreenLoading
import com.example.androiddevchallenge.ui.view.LoadingContent

// Start building your app here!
@Composable
fun PetScreen(
    petLiveData: LiveData<UiState<List<Pet>>>,
    onRefreshPets: () -> Unit,
    showDetail: (Pet) -> Unit
) {

    val pets by petLiveData.observeAsState()

    Scaffold(
        topBar = {
            val title = stringResource(id = R.string.app_name)
            TopAppBar(
                title = { Text(text = title) }
            )
        },
        content = { innerPadding ->
            val modifier = Modifier.padding(innerPadding)

            pets?.let {
                LoadingContent(
                    empty = it.initialLoad,
                    emptyContent = { FullScreenLoading() },
                    loading = it.loading,
                    onRefresh = { onRefreshPets() },
                    content = {
                        PetContent(
                            pets = it,
                            onRefresh = { onRefreshPets() },
                            modifier = modifier,
                            showDetail = showDetail
                        )
                    }
                )
            }
        }
    )
}

@Composable
fun PetContent(
    pets: UiState<List<Pet>>,
    onRefresh: () -> Unit,
    modifier: Modifier,
    showDetail: (Pet) -> Unit
) {
    if (pets.data != null) {
        PetList(pets.data, modifier, showDetail)
    } else if (!pets.hasError) {
        // if there are no posts, and no error, let the user refresh manually
        TextButton(onClick = onRefresh, modifier.fillMaxSize()) {
            Text("Tap to load content", textAlign = TextAlign.Center)
        }
    } else {
        // there's currently an error showing, don't show any content
        Box(modifier.fillMaxSize()) { /* empty screen */ }
    }
}

@Composable
fun PetList(pets: List<Pet>, modifier: Modifier, showDetail: (Pet) -> Unit) {
    val petPopular = pets.subList(0, 3)

    LazyColumn(modifier = modifier) {
        item { PopularPetSection(petPopular, showDetail) }
        item { AllPetSection(pets, showDetail) }
    }
}

@Composable
fun AllPetSection(pets: List<Pet>, showDetail: (Pet) -> Unit) {
    Column {

        Text(
            modifier = Modifier.padding(16.dp),
            text = "All Pets",
            style = MaterialTheme.typography.subtitle1
        )

        Column {
            pets.reversed().forEach { pet ->
                PetCard(pet, showDetail)
                PetListDivider()
            }
        }
    }
}

@Composable
fun PopularPetSection(pets: List<Pet>, showDetail: (Pet) -> Unit) {
    Column {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Popular Pets",
            style = MaterialTheme.typography.subtitle1
        )

        LazyRow(modifier = Modifier.padding(end = 16.dp)) {
            items(pets) { pet ->
                PopularPetCard(
                    pet,
                    Modifier.padding(start = 16.dp, bottom = 16.dp),
                    showDetail = showDetail
                )
            }
        }
        PetListDivider()
    }
}

@Composable
fun PetCard(pet: Pet, showDetail: (Pet) -> Unit) {

    Row(
        Modifier
            .clickable(onClick = { showDetail(pet) })
            .padding(16.dp)
    ) {

        Image(
            painter = painterResource(pet.imageId),
            contentDescription = null, // decorative
            modifier = Modifier
                .padding(end = 16.dp)
                .size(80.dp, 80.dp)
                .clip(MaterialTheme.shapes.small)
        )

        Column {
            Text(
                text = pet.name,
                style = MaterialTheme.typography.h6,
            )
            Text(
                text = pet.desc,
                style = MaterialTheme.typography.body1,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
fun PopularPetCard(pet: Pet, modifier: Modifier, showDetail: (Pet) -> Unit) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.size(280.dp, 240.dp)
    ) {
        Column(modifier = Modifier.clickable(onClick = { showDetail(pet) })) {

            Image(
                painter = painterResource(pet.imageId),
                contentDescription = null, // decorative
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            )

            Text(
                text = pet.name,
                style = MaterialTheme.typography.h6,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(start = 16.dp, top = 8.dp)
            )
        }
    }
}

/**
 * Full-width divider with padding for [PetList]
 */
@Composable
private fun PetListDivider() {
    Divider(
        modifier = Modifier.padding(horizontal = 14.dp),
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
    )
}
