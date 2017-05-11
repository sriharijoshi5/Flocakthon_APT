
%%%%% Reading of a RGB image 

count = 0;
while 1
    
    i = imread('C:\Users\srihariJoshi\Downloads\webcamimagesave\Images\hii');
    
    % Convert 3D RGB image to 2D image with black, white and grays
    I = rgb2gray(i);
    
    % Threshold function. Converts all pixels below threshold to black, and above to white. No grays.
    BW = im2bw(I);
    
    % Display image
    figure, imshow(BW)
    
    [n1 n2] = size(BW);
    
    r = floor(n1/10);
    c = floor(n2/10);
    
    x1 = 1; 
    x2 = r;

    s = r * c;

    fid = fopen('aa.txt', 'r');
    fopen('aa.txt', 'w');
    
    for i = 1 : 10
      
       y1 = 1; 
       y2 = c;
       
       for j = 1 : 10
           
            if (y2 <= c || y2 >= 9 * c) || (x1 == 1 || x2 == r * 10)
               
               loc = find (BW(x1:x2, y1:y2) == 0);
               [o p] = size(loc);
               pr = o * 100 / s;

            else
    
                % Creates verbatim copy of outputs 
                diary ('aa.txt');
                
                
                if pr <= 100
                   
                   BW (x1:x2, y1:y2) = 0;
                   r1 = x1;
                   r2 = x2;
                   pr1 = 0;
                   
                else            
            
                   diary ('aa.txt')
                        
                end
                
                imshow(BW);
            end

            y1 = y1 + c;
            y2 = y2 + c;
        end
        
     x1 = x1 + r;
     x2 = x2 + r;
end

figure, imshow(BW)

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%



%%%%% detection of face object
p = 0;
j = 0;
k = 0;
L = bwlabel(BW,8);
BB  = regionprops(L, 'BoundingBox');
BB1 = struct2cell(BB);
BB2 = cell2mat(BB1);

[s1 s2] = size(BB2);
mx = 0;
for k = 3:4:s2 - 1
 
    p = BB2(1, k) * BB2(1, k + 1);
 
    if p > mx && (BB2(1, k) / BB2(1, k + 1)) < 1.8
 
        mx = p;
        j = k;
 
    end

end

figure, imshow(I);
hold on;

pause(.5)

try
rectangle('Position', [BB2(1, j - 2), BB2(1, j - 1), BB2(1, j), BB2(1, j + 1)],'EdgeColor','r' )

catch 
    disp('low')
    disp('low')
    disp('low')
    disp('low')
    break;
end
 
count = count + 1;

if count <= 1

    dos('C:\Users\srihariJoshi\Desktop\finalpro\ard.bat')
end

tic;
pause(5);
toc;

end
