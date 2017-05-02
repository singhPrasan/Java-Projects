var origImg = null;
var imgForGray = null;
var imgForRed = null;
var imgForNegative = null;
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
    imgForNegative = new SimpleImage(file);
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


function doRainbow(){
    if(isImageLoaded(imgForRainbow) === true){
        applyRainbow();
        imgForRainbow.drawTo(canvas);
    }else{
        alert("Image not loaded successfully! Try uploading again.");   
    }
}
function applyRainbow(){
    for(pixel of imgForRainbow.values()){
        var avg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue())/3;
        var imgHeight = imgForRainbow.getHeight();
        var Y = pixel.getY();
        if(avg < 128){
            if( Y < imgHeight/7 ){
                //Set Red Filter
                pixel.setRed(2*avg);
                pixel.setGreen(0);
                pixel.setBlue(0);
            }else if( Y >= imgHeight/7 && Y < 2*imgHeight/7){
                //Set Orange Filter
                pixel.setRed(2*avg);
                pixel.setGreen(0.8*avg);
                pixel.setBlue(0);
            }else if( Y >= 2*imgHeight/7 && Y < 3*imgHeight/7){
                //Set Yellow Filter
                pixel.setRed(2*avg);
                pixel.setGreen(2*avg);
                pixel.setBlue(0);
            }else if( Y >= 3*imgHeight/7 && Y < 4*imgHeight/7){
                //Set Green Filter
                pixel.setRed(0);
                pixel.setGreen(2*avg);
                pixel.setBlue(0);
            }else if( Y >= 4*imgHeight/7 && Y < 5*imgHeight/7){
                //Set Blue Filter
                pixel.setRed(0);
                pixel.setGreen(0);
                pixel.setBlue(2*avg);
            }else if( Y >= 5*imgHeight/7 && Y < 6*imgHeight/7){
                //Set Indigo Filter
                pixel.setRed(0.8*avg);
                pixel.setGreen(0);
                pixel.setBlue(2*avg);
            }else{
                //Set Violet Filter
                pixel.setRed(1.6*avg);
                pixel.setGreen(0);
                pixel.setBlue(1.6*avg);
            }
        }else{
            if( Y < imgHeight/7 ){
                //Set Red Filter
                pixel.setRed(255);
                pixel.setGreen(2*avg- 255);
                pixel.setBlue(2*avg- 255);
            }else if( Y >= imgHeight/7 && Y < 2*imgHeight/7){
                //Set Orange Filter
                pixel.setRed(255);
                pixel.setGreen(1.2*avg-51);
                pixel.setBlue(2*avg-255);
            }else if( Y >= 2*imgHeight/7 && Y < 3*imgHeight/7){
                //Set Yellow Filter
                pixel.setRed(255);
                pixel.setGreen(255);
                pixel.setBlue(2*avg-255);
            }else if( Y >= 3*imgHeight/7 && Y < 4*imgHeight/7){
                //Set Green Filter
                pixel.setRed(2*avg-255);
                pixel.setGreen(255);
                pixel.setBlue(2*avg-255);
            }else if( Y >= 4*imgHeight/7 && Y < 5*imgHeight/7){
                //Set Blue Filter
                pixel.setRed(2*avg-255);
                pixel.setGreen(2*avg-255);
                pixel.setBlue(255);
            }else if( Y >= 5*imgHeight/7 && Y < 6*imgHeight/7){
                //Set Indigo Filter
                pixel.setRed(1.2*avg-51);
                pixel.setGreen(2*avg-255);
                pixel.setBlue(255);
            }else{
                //Set Violet Filter
                pixel.setRed(0.4*avg+153);
                pixel.setGreen(2*avg-255);
                pixel.setBlue(0.4*avg+153);
            }
        }
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