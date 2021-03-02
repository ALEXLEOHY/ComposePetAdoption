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
package com.example.androiddevchallenge.data

import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.Pet

val hula = Pet(0, "Hula", "Hula is a super smart amazing dog with so much love to give. Hula is well trained knows all commands sit, heal, go to your bed, stay on your bed, go to your crate, paw she knows it all. Hula is very protective and so loving g she needs a experienced owner who keeps her in check and loves her. She loves to play with toys she is a great fetcher and will be the most loyal dog you will ever know. We require that you meet with our trainer and that you take 15 training sessions with our trainer. You will love our trainer he has been training for 30 years. Give Hula a chance to be your forever best friend. She was abandoned and given up and hit in the face and she has come through it all.", false, R.drawable.hula)
val lucas = Pet(1, "Lucas", "Lucas was left in a balcony tied up and all because they had a baby and decided he would not be ok with their baby. He never dis anything wrong. After the trauma they caused Lucas would not allow a collar on his neck who could blame him. Good news we brought Luca to the best trainer in LA and Luca walks on a Kesha and wares the right gear. Lucas would love all the love he missed out in as a baby. Lucas would love to be your only dog, he has so much live to give. He loves to be loved he gets so happy when he is being petted and he loves his belly rubs. He is local to LA and sorry we don’t ship dogs out of state. He is truly incredible and deserves a loving experienced bully breed owner.", false, R.drawable.lucas)
val percious = Pet(2, "Precious", "Precious is a loving pup who just wants to be near her people. She likes sitting next to you on the couch watching tv or reading a book, she like finding a free corner to sit and watch you cook in the kitchen, and she likes sitting in the back seat to run errands. She's energetic and friendly and is searching for her perfect permanent family!", false, R.drawable.percious)
val buster = Pet(
    3, "Buster",
    "Buster loves affection, hikes and treats! He's playful and loves hiking in the canyons.His favorite dinner dish is sloppy joes. After dinner he's always excited to cuddle on the couch and watch The Voice. He is crate trained,he walks well on a leash now and wears a front clip harness to walk with. He is dog selective, if introduced correctly he can be good with another dog of his own size. He gets groomed every six weeks for anal gland expression and shedding. He is Incredibly loving, very smart and figured out how to get into our fridge. He loves to cuddle. He is very good with my son and checks on him. He has a HUGE prey drive so no cats. Give us a call to meet this handsome fellow!\n" +
        "\n" +
        "Check out my videos:\n" +
        "http://www.magisto.com/video/IkMdMAUCEzIyDwBhCzE\n" +
        "\n" +
        "https://youtu.be/1DvvqHdpyfw",
    false, R.drawable.buster
)

val riley = Pet(4, "Riley", "Riley was found as a stray by her foster, she is aprox. 1 year old, very playful, and will be the perfect fit for an active family who is available to continue her puppy training.", false, R.drawable.riley)

val petList = listOf(hula, lucas, percious, buster, riley)
