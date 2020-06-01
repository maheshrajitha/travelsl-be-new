package com.travelsl.travelsl.util;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.azure.storage.blob.specialized.BlockBlobClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
public class ImageUploadClient {

    @Autowired
    private BlobServiceClient blobServiceClient;

    public void uploadImage(String image , String locationId) throws NoSuchAlgorithmException {
        BlobContainerClient blobContainerClient = blobServiceClient.getBlobContainerClient("locationimages");
        BlockBlobClient blockBlobClient = blobContainerClient.getBlobClient(locationId).getBlockBlobClient();
        byte[] decodedImage = Base64.getDecoder().decode(image);
        blockBlobClient.
                uploadWithResponse(new ByteArrayInputStream(decodedImage),
                        decodedImage.length,new BlobHttpHeaders().setContentType("image/png")
                        ,null,null
                        ,null,null,null,null);
    }
}
