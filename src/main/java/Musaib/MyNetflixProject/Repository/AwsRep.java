package Musaib.MyNetflixProject.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.protocols.xml.AwsS3ProtocolFactory;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.time.Duration;
@Repository
public class AwsRep  {
    private String bucket = "netflix-musaib";
    @Autowired
    private AwsCredentialsProvider awsCredentialsProvider;

    public String getPreSignedUrl(String path, int durationInSeconds) {
        S3Presigner s3Presigner = S3Presigner
                .builder()
                .region(Region.AP_SOUTH_1)
                .credentialsProvider(awsCredentialsProvider).build();
        GetObjectRequest getObjectRequest = GetObjectRequest
                .builder()
                .key(path)
                .bucket(bucket)
                .build();
        GetObjectPresignRequest getObjectPresignRequest = GetObjectPresignRequest
                .builder()
                .signatureDuration(Duration.ofSeconds(durationInSeconds))
                .getObjectRequest(getObjectRequest)
                .build();
        PresignedGetObjectRequest request = s3Presigner
                .presignGetObject(getObjectPresignRequest);
        return request.url().toString();
    }

}