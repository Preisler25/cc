package preisler.com.crazy_counter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import preisler.com.crazy_counter.emotion.EmotionRepository;
import preisler.com.crazy_counter.emotion.EmotionService;

@SpringBootTest
class CrazyCounterApplicationTests {

	@Mock
	private EmotionRepository emotionRepository;

	@InjectMocks
	private EmotionService emotionService;

	// Initialize mocks
	public CrazyCounterApplicationTests() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void contextLoads() {
		// Basic test to check if the Spring context loads correctly
	}
	
}