package phani.springframework.springfileuploadapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.server.UnsupportedMediaTypeStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class FileUploadExceptionAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Object> handleMaxSizeException(MaxUploadSizeExceededException exception) {
        return new ResponseEntity<>("File too Large", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnsupportedMediaTypeStatusException.class)
    public ResponseEntity<Object> handleUnSupportedMediaException(UnsupportedMediaTypeStatusException exception) {
        return new ResponseEntity<>("Unsupported Mediatype", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }
}
