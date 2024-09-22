package preisler.com.crazy_counter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import preisler.com.crazy_counter.models.Emotion;
import preisler.com.crazy_counter.emotion.EmotionEntity;
import preisler.com.crazy_counter.emotion.EmotionRepository;
import preisler.com.crazy_counter.emotion.EmotionService;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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

	// Test for the EmotionService
	@Test
	void testEmotionService() throws ParseException {
		// Use SimpleDateFormat to handle Date <-> String conversion
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr1 = "2021-01-01";  // String date for testing

		// Convert dateStr1 to Date
		Date date1 = formatter.parse(dateStr1);  // Parse the string date to a Date object
		Date date2 = new Date();  // Current date

		// Mock the behavior of the EmotionRepository with Date objects
		EmotionEntity emotion1 = new EmotionEntity("happy", "😀", date1, 1);
		EmotionEntity emotion2 = new EmotionEntity("sad", "😢", date2, 1);

		// Mock repository responses using Date for the date field
		when(emotionRepository.findByDateAndUserId(date1, 1))
				.thenReturn(Arrays.asList(emotion1));

		// Test GetEmotionByDate method
		List<EmotionEntity> emotions = emotionService.GetEmotionByDate(formatter.format(date1), 1);
		assertEquals(1, emotions.size());

		// Test AddNewEmotion method
		emotionService.AddNewEmotion(1, "sad", "😢", formatter.format(date1), 1);
		when(emotionRepository.findByDateAndUserId(date1, 1))
				.thenReturn(Arrays.asList(emotion1, emotion2));
		emotions = emotionService.GetEmotionByDate(formatter.format(date1), 1);
		assertEquals(2, emotions.size());

		// Test deleteEmotionById method
		emotionService.deleteEmotionById(1);
		when(emotionRepository.findByDateAndUserId(date1, 1))
				.thenReturn(Arrays.asList(emotion2));
		emotions = emotionService.GetEmotionByDate(formatter.format(date1), 1);
		assertEquals(1, emotions.size());
	}
}
