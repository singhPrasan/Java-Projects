var origImg = null;
var imgForGray = null;
var imgForRed = null;
var imgForNegative = null;
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
    imgForNegative = new SimpleImage(file);
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


function doRed(){
    if(isImageLoaded(imgForRed) === true){
        applyRed();
        imgForRed.drawTo(canvas);
    }else{
        alert("Image not loaded successfully! Try uploading again.");
    }
}

function applyRed(){
    for(pixel of imgForRed.values()){
        var avg = (pixel.getRed() + pixel.getGreen() + pixel.getGreen())/3;
        if( avg < 128){
            pixel.setRed(2*avg);
            pixel.setGreen(0);
            pixel.setBlue(0);
        }else{
            pixel.setRed(255);
            pixel.setGreen(2*avg - 255);
            pixel.setBlue(2*avg - 255);
        }
    }
}
function doNegative(){
    if(isImageLoaded(imgForNegative) === true){
        applyNegative();
        imgForNegative.drawTo(canvas);
    }else{
        alert("Image not loaded successfully! Try uploading again.");
    }
}
function applyNegative(){
    for(pixel of imgForNegative.values()){
        pixel.setRed(255 - pixel.getRed());
        pixel.setGreen(255 - pixel.getGreen());
        pixel.setBlue(255 - pixel.getBlue());
    }
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