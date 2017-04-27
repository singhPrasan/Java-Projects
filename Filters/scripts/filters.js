var origImg = null;
var imgForGray = null;
var imgForRed = null;
var imgForRainbow = null;
var canvas = document.getElementById('can1');

function upload(){
    //get input file
    var file = document.getElementById('f1');
    //store and draw the input file to the canvas element
    origImg = new SimpleImage(file);
    origImg.drawTo(canvas);
    //make copies of the original image
    imgForGray = new SimpleImage(file);
    imgForRed = new SimpleImage(file);
    imgForRainbow = new SimpleImage(file);
}

function doGray(){
    //Check whether the imgForGray has loaded successfully or not
    //If yes : Apply grayscale algorithm
    //if No : Alert the user and abort
    if(isImageLoaded(imgForGray) === true){
        applyGray();
        //Draw the grayScaled image to canvas
        imgForGray.drawTo(canvas);
    }else{
        alert("Image not loaded successfully! Try uploading again.");
    }
}

function applyGray(){
    //For every pixel of the image
    for(pixel of imgForGray.values()){
       //Find the average RGB count
       var avg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
       //Set Red, Green and Blue to the average
       pixel.setRed(avg);
       pixel.setGreen(avg);
       pixel.setBlue(avg);
    }
}

function secondfilter(){
    alert("second filter applied");
}

function thirdfilter(){
    alert("third filter applied");
}

function reset(){
    if(isImageLoaded(origImg) === true ){
        clearCanvas();
        origImg.drawTo(canvas);
    }else{
        alert("Image not loaded successfully! Try uploading again.")
    }
}

function isImageLoaded(img) {
    if(img == null || !img.complete()){
        return false;
    }else{
        return true;
    }
}

function clearCanvas(){
    var ctx = canvas.getContext('2d');
    ctx.clearRect(0,0, canvas.width, canvas.height);
}