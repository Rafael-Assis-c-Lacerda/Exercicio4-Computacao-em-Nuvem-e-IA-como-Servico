package exercicio4ti2;

import com.azure.ai.vision.face.FaceClient;
import com.azure.ai.vision.face.FaceClientBuilder;
import com.azure.ai.vision.face.models.FaceDetectionModel;
import com.azure.ai.vision.face.models.FaceDetectionResult;
import com.azure.ai.vision.face.models.FaceRectangle;
import com.azure.ai.vision.face.models.FaceRecognitionModel;
import com.azure.core.credential.KeyCredential;

import java.util.List;

public class AnalisadorDeFaces {
	/*
	 * Ola meu nome é Rafael e essa é a minha atividade, para quem estiver vendo ela vou avisar, eu na realidade estava tentando fazer um analisador de emoções, porem após tentar muito eu não consegui,
	 * mas não por que eu não tentei e sim porque o plano da microsoft azure de analise de fases não cobre isso, sendo possivel apenas fazer uma detcção de faces.
	 * 
	 * Eu realmente pesquisei e tentei, mas isso é algo que está fora do meu alcance e está relacionado com um problema de disponibilidade do serviço, um problema da Instituição.
	 */

	//descomente isso
	
    private static final String SUBSCRIPTION_KEY = "";
    private static final String ENDPOINT = "";

    public static void main(String[] args) {
        FaceClient client = authenticate(ENDPOINT, SUBSCRIPTION_KEY);
        detectFaces(client);
        System.out.println("\nDetecção de faces concluída.");
    }

    public static FaceClient authenticate(String endpoint, String key) {
        return new FaceClientBuilder().endpoint(endpoint).credential(new KeyCredential(key)).buildClient();
    }

    public static void detectFaces(FaceClient faceClient) {
        System.out.println("======== DETECTAR FACES ========");
        String imageUrl = "https://raw.githubusercontent.com/Azure-Samples/cognitive-services-sample-data-files/master/Face/images/detection1.jpg";

        List<FaceDetectionResult> detectedFaces = faceClient.detect(imageUrl, FaceDetectionModel.DETECTION_03, FaceRecognitionModel.RECOGNITION_04, false);

        System.out.println("\nDetectadas " + detectedFaces.size() + " face(s) na imagem: " + imageUrl);

        int faceCount = 0;
        for (FaceDetectionResult face : detectedFaces) {
            faceCount++;
            FaceRectangle rect = face.getFaceRectangle();
            System.out.println("\n--- Face #" + faceCount + " ---");
            System.out.printf("Localização (Retângulo): Topo=%d, Esquerda=%d, Largura=%d, Altura=%d%n",
                rect.getTop(), rect.getLeft(), rect.getWidth(), rect.getHeight());
        }
    }

}
