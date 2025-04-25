/*
DataParser.sc
Handles loading and parsing of timestamped sensor data from files
*/

DataParser {
	classvar <>dataPath;
	
	*initClass {
		dataPath = "data/".resolveRelative;
	}
	
	*parseFile { |filename|
		var file, data = List[];
		var path = this.dataPath +/+ filename;
		
		if(File.exists(path).not) {
			"File does not exist: %".format(path).error;
			^nil
		};
		
		file = File(path, "r");
		
		// Read each line, expecting timestamp, [data array] format
		file.linesDo({ |line|
			var parts, timestamp, values;
			
			// Skip empty lines or comments
			if(line.size == 0 || line[0] == $#) { ^nil };
			
			// Split timestamp and data array
			parts = line.split($,);
			if(parts.size >= 2) {
				timestamp = parts[0].interpret;
				
				// Extract the array of values
				values = parts[1..].join(",").interpret;
				
				if(timestamp.isNumber && values.isArray) {
					data.add([timestamp, values]);
				} {
					"Invalid data format in line: %".format(line).warn;
				};
			};
		});
		
		file.close;
		^data;
	}
	
	*getFiles {
		var path = this.dataPath;
		^PathName(path).files.collect({ |file| file.fileName });
	}
	
	// Extract a specific dimension of data across all timestamps
	*extractDimension { |data, dimension|
		^data.collect({ |item| [item[0], item[1][dimension]] });
	}
	
	// Normalize data to 0-1 range
	*normalizeData { |data|
		var values = data.collect({ |item| item[1] });
		var min = values.minItem;
		var max = values.maxItem;
		var range = max - min;
		
		if(range == 0) { ^data };
		
		^data.collect({ |item|
			[item[0], (item[1] - min) / range]
		});
	}
}
