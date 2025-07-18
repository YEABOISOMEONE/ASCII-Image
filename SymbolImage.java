import java.awt.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.JFrame;
public class SymbolImage {


    public static void main(String[] args) throws IOException{
        File f = new File("trying4.jpg");
        PrintWriter p = new PrintWriter(new File("trying.html"));
        BufferedImage img = ImageIO.read(f);

        p.println("<html lang = \"en\">");
        p.println("<head>");
        p.println("    <meta charset=\"UTF-8\">");
        p.println("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        p.println("    <style>");
        p.println("        body {");
        p.println("            font-family: Arial, sans-serif;");
        p.println("            margin: 20px;");
        p.println("            background-color: #f0f0f0;");
        p.println("            display: flex;");
        p.println("            flex-direction: column;");
        p.println("            align-items: center;");
        p.println("        }");
        p.println("");
        p.println("        canvas {");
        p.println("            background-color: white;");
        p.println("            border-radius: 8px;");
        p.println("            box-shadow: 0 2px 10px rgba(0,0,0,0.1);");
        p.println("            cursor: pointer;");
        p.println("        }");
        p.println("    </style>");
        p.println("</head>");
        p.println("<body>");
        p.println("    <canvas id=\"gridCanvas\" width=\"710\" height=\"1160\"></canvas>");
        p.println("    <script>");
        p.println("        const canvas = document.getElementById(\"gridCanvas\");");
        p.println("        const c =  canvas.getContext(\"2d\");");
        p.println("");
        p.println("        const cols = "+img.getWidth()+";");
        p.println("        const rows = "+img.getHeight()+";");
        p.println("        const gap = 0;");
        p.println("        const padding = 0;");
        p.println("");
        p.println("        const cellWidth = (canvas.width - (2 * padding) - ((cols - 1) * gap))/cols;");
        p.println("        const cellHeight = (canvas.height - (2 * padding) - ((rows - 1) * gap))/rows;");
        p.println("");
        p.println("        const twoDArray = [");


        for(int i = 0; i < img.getHeight(); i++){
            p.print("                           [");
            for(int j = 0; j < img.getWidth(); j++){
                int color = img.getRGB(j,i);
                
                int blue = color & 0xFF;
                int red = (color>>16) & 0xFF;
                int green = (color>>8) & 0xFF;
                String redString = Integer.toString(red,16);
                String blueString = Integer.toString(blue,16);
                String greenString = Integer.toString(green,16);
                if(redString.length()==0){
                    redString = "00";
                }else if (redString.length()==1){
                    redString = "0"+redString;
                }
                if(blueString.length()==0){
                    blueString = "00";
                }else if (blueString.length()==1){
                    blueString = "0"+blueString;
                }
                if(greenString.length()==0){
                    greenString = "00";
                }else if (greenString.length()==1){
                    greenString = "0"+greenString;
                }
                p.printf("\"#"+redString+greenString+blueString+"\"");
                if( j <img.getWidth()-1){
                    p.print(",");
                }
        /*
                double gray = (red * 299 + green * 587 + blue * 114)/255000.0;
                String brightness =  " `.-':_,^=;><+!rc
                /z?sLTv)J7(|Fi{C}fI31tlu[neoZ5Yxjya]2ESwqkP6h9d4VpOGbUAKXHm8RD#$Bg0MNWQ%&@";
                brightness = new StringBuilder(brightness).reverse().toString();
                double[] list = {-.1, 0.0751, 0.0829, 0.0848, 0.1227, 0.1403, 0.1559, 0.185, 0.2183, 0.2417, 0.2571, 0.2852, 0.2902, 0.2919, 0.3099, 0.3192, 0.3232, 0.3294, 0.3384, 0.3609, 0.3619, 0.3667, 0.3737, 0.3747, 0.3838, 0.3921, 0.396, 0.3984, 0.3993, 0.4075, 0.4091, 0.4101, 0.42, 0.423, 0.4247, 0.4274, 0.4293, 0.4328, 0.4382, 0.4385, 0.442, 0.4473, 0.4477, 0.4503, 0.4562, 0.458, 0.461, 0.4638, 0.4667, 0.4686, 0.4693, 0.4703, 0.4833, 0.4881, 0.4944, 0.4953, 0.4992, 0.5509, 0.5567, 0.5569, 0.5591, 0.5602, 0.5602, 0.565, 0.5776, 0.5777, 0.5818, 0.587, 0.5972, 0.5999, 0.6043, 0.6049, 0.6093, 0.6099, 0.6465, 0.6561, 0.6595, 0.6631, 0.6714, 0.6759, 0.6809, 0.6816, 0.6925, 0.7039, 0.7086, 0.7235, 0.7302, 0.7332, 0.7602, 0.7834, 0.8037, 1.01};
                for(int k = 0; k < list.length; k++){
                    if(gray>list[k]){
                        p.print("<span style=\"color:rgb("+red+","+green+","+blue+");\"><b>"+brightness.substring(k,k+1)+"</b></span>");
                        break;
                    }
        */
                }
                p.print("]");
                if(i<img.getHeight()-1){
                    p.println(",");
                }else{
                    p.println();
                }

            }
            //p.println("<br>");
        p.println("                                    ];");
    
        p.println();
        //}    
        // p.println("</body>");
        p.println("");
        p.println("        function draw(){");
        p.println("            c.clearRect(0,0,canvas.width,canvas.height);");
        p.println("            for(let i = 0; i < rows; i++){");
        p.println("                for(let j = 0; j < cols; j++){");
        p.println("                    var x = padding + i*(cellWidth+gap);");
        p.println("                    var y = padding + j*(cellHeight+gap);");
        p.println("                    c.fillStyle = twoDArray[i][j];");
        p.println("                    c.fillRect(y,x,cellHeight,cellWidth);");
        p.println("                }");
        p.println("            }");
        p.println("        }");
        p.println("        ");
        p.println("        draw();");
        p.println("    </script>");
        p.println("</body>");
        p.println("");
        p.println("</html>");
        p.close();

        
    }
}
