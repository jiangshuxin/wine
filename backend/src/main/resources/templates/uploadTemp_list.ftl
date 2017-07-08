<@dt.init>

  <!-- Upload file form -->
  <form id="upload-file-form-module">
  	  <div class="form-group">
	    <label for="upload-file-input-module" class="col-md-2 form-label">Upload your file:</label>
	    <div class="col-md-10">
	    	<input id="upload-file-input-module" type="file" name="uploadFile" accept="*" class="form-control"/>
	    </div>
      </div>
      <br />
      <span id="upload-file-message-module"></span>
  </form>
  <br />
  <hr />
  Proudly handcrafted by
  <a href='http://www.99wuxian.com'>99wuxian</a>
  
  <!-- Javascript functions -->
  <script>
  
    // bind the on-change event for the input element (triggered when a file
    // is chosen)
    $(document).ready(function() {
      $("#upload-file-input-module").on("change", upload);
    });
    
    /**
     * Upload the file sending it via Ajax at the Spring Boot server.
     */
    function upload() {
      $.ajax({
        url: "/upload/discardExist",
        type: "POST",
        data: new FormData($("#upload-file-form-mdse")[0]),
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        success: function () {
          // Handle upload success
          $("#upload-file-message-mdse").text("File succesfully uploaded");
        },
        error: function () {
          // Handle upload error
          $("#upload-file-message-mdse").text(
              "File not uploaded (perhaps it's too much big)");
        }
      });
    } // function uploadFile
  </script>
  
</@dt.init>