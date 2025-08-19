#include <stdio.h> 
#include <math.h> 
#include <string.h> 
 
int main() { 
    int i, j, k, count, err_pos = 0, flag = 0; 
    char data[8], dw[13], cw[13]; // data = 7 bits, dw/cw = 12 bits + 1 (1-based index) 
 
    printf("Enter data as binary bit stream (7 bits):\n"); 
    scanf("%s", data); 
 
    // Initialize with '0' 
    for (i = 0; i < 13; i++) { 
        dw[i] = '0'; 
        cw[i] = '0'; 
    } 
 
    // Step 1: Insert data bits into correct positions 
    for (i = 1, j = 0, k = 0; i <= 12; i++) { 
        if (i == (1 << j)) { // Parity bit positions 
            dw[i] = '0'; // placeholder 
            j++; 
        } else { 
            dw[i] = data[k++]; 
        } 
    } 
 
    // Step 2: Calculate parity bits 
    for (i = 0; i < 4; i++) { 
        count = 0; 
        for (j = (1 << i); j <= 12; j += (1 << (i + 1))) { 
            for (k = 0; k < (1 << i) && (j + k) <= 12; k++) { 
                if (dw[j + k] == '1') { 
                    count++; 
                } 
            } 
        } 
        dw[(1 << i)] = (count % 2 == 0) ? '0' : '1'; 
    } 
 
    // Step 3: Print generated codeword 
    printf("Generated code word is:\n"); 
    for (i = 1; i <= 12; i++) { 
        printf("%c", dw[i]); 
    } 
 
    // Step 4: Read received codeword 
    printf("\n\nEnter the received Hamming code (12 bits):\n"); 
    scanf("%s", &cw[1]); // store starting at index 1 
 
    // Step 5: Detect error position 
    for (i = 0; i < 4; i++) { 
        count = 0; 
        for (j = (1 << i); j <= 12; j += (1 << (i + 1))) { 
            for (k = 0; k < (1 << i) && (j + k) <= 12; k++) { 
                if (cw[j + k] == '1') { 
                    count++; 
                } 
            } 
        } 
        if (count % 2 != 0) { 
            err_pos += (1 << i); 
        } 
    } 
    // Step 6: Error handling 
    if (err_pos == 0) { 
        printf("\n\nThere is no error in the received code word.\n"); 
    } else { 
        if (cw[err_pos] == dw[err_pos]) { 
            printf("\n\nThere are 2 or more errors in the received code...\n"); 
            printf("Sorry...! Hamming code cannot correct 2 or more errors.\n"); 
            flag = 1; 
        } else { 
            printf("\n\nThere is an error in bit position %d of the received code word.\n", err_pos); 
            if (flag == 0) { 
                cw[err_pos] = (cw[err_pos] == '1') ? '0' : '1'; 
                printf("\nCorrected code word is:\n"); 
                for (i = 1; i <= 12; i++) { 
                    printf("%c", cw[i]); 
                } 
            } 
        } 
    } 
 
    printf("\n\n"); 
    return 0; 
}
