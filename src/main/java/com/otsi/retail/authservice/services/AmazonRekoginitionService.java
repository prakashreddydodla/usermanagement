package com.otsi.retail.authservice.services;

import java.nio.ByteBuffer;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.DetectLabelsRequest;
import com.amazonaws.services.rekognition.model.DetectLabelsResult;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.Label;

@Service
public class AmazonRekoginitionService {

	private AmazonRekognition client;

	@PostConstruct
	public void init() {
		BasicAWSCredentials credentials = new BasicAWSCredentials("AKIAYZOXA3O4HNGVWCQL", "j0p2Sk5u4UDtEilylTxYHgxy3x37HdG7E3G0I+2Z");//need to place credentials

		client = AmazonRekognitionClientBuilder.standard().withRegion(Regions.EU_WEST_1)
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
	}

	public List<Label> detectLables(MultipartFile imageFile) throws Exception {
	
		try {
			Image image = new Image();
			image.setBytes(ByteBuffer.wrap(imageFile.getBytes()));

			DetectLabelsRequest detectLabelsRequest = new DetectLabelsRequest();
			detectLabelsRequest.setImage(image);
			DetectLabelsResult lablesResponce = client.detectLabels(detectLabelsRequest);
			return lablesResponce.getLabels();
		} catch (Exception e) {
			throw new Exception();
		}
	}

}
