
// Using GSAP for animation

// NAVBAR
TweenMax.from(".navbar", 1.5,  {
    delay:2.5,
    opacity:0,
    y:20,
})
 // TEXT
 TweenMax.from(".text h1 .text-bar", 1.5,  {
    delay:1.6,
    y:"135%",
})
TweenMax.from(".text p .text-bar", 1.5,  {
    delay:2,
    y:"100%",
})
// button
TweenMax.from(".btn-container .btn", 1.5,  {
    delay:2,
    y:"190%",
})

// sliding functionality 
let image = [
    `https://images.unsplash.com/photo-1485462537746-965f33f7f6a7?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mzh8fGZhc2hpb258ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60`,
    `https://plus.unsplash.com/premium_photo-1661645955394-54e20f20ece6?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8ZHJlc3MlMjBtYW58ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60`
]

let index = 0;

let slidShow = () => {

    console.log("working")

    let container = document.querySelector(".img-wrapper");

    id = setInterval(() => {

        if (index === image.length) {
            index = 0;
        }
        container.innerHTML = null;
        avtar = document.createElement("img");
        avtar.src = image[index]
        container.append(avtar);
        index++;
    }, 4000)

}

slidShow();