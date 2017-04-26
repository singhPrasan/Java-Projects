function makeGray(){
    for(var pixel of fgImg.values()){
        var avg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue())/3;
        pixel.setRed(avg);
        pixel.setGreen(avg);
        pixel.setBlue(avg);
    }
    var canvas = document.getElementById('can2');
    fgImg.drawTo(canvas);
}