using System;
using System.IO;

namespace Test
{
  /// <summary>Writable stream with a maximum supported byte
  /// size.</summary>
  public sealed class LimitedMemoryStream : Stream
  {
    private readonly Test.DelayingStream ms;
    private readonly int maxSize;

    /// <summary>
    /// Initializes a new instance of the <see cref="LimitedMemoryStream"/> class.
    /// </summary>
    /// <param name="maxSize"></param>
    public LimitedMemoryStream(int maxSize)
    {
      if (maxSize < 0)
      {
        throw new ArgumentException(
          "maxSize (" + maxSize + ") is not greater or equal to 0");
      }
      this.ms = new Test.DelayingStream();
      this.maxSize = maxSize;
    }

    public new void Dispose()
    {
      this.ms.Dispose();
    }

    /// <inheritdoc/>
    public override long Length => this.ms.Length;

    /// <inheritdoc/>
    public override long Seek(long pos, SeekOrigin origin)
    {
      throw new NotSupportedException();
    }

    /// <inheritdoc/>
    public override void SetLength(long len)
    {
      if (len > this.maxSize)
      {
        throw new NotSupportedException();
      }
      this.ms.SetLength(len);
    }

    /// <inheritdoc/>
    public override long Position
    {
      get => this.ms.Position;

      set => throw new NotSupportedException();
    }

    /// <inheritdoc/>
    public override bool CanRead => false;

    /// <inheritdoc/>
    public override bool CanSeek => false;

    /// <inheritdoc/>
    public override bool CanWrite => this.ms.CanWrite;

    /// <inheritdoc/>
    public override int Read(byte[] bytes, int offset, int count)
    {
      throw new NotSupportedException();
    }

    /// <inheritdoc/>
    public override void Flush()
    {
      this.ms.Flush();
    }

    /// <inheritdoc/>
    public override void Write(byte[] bytes, int offset, int count)
    {
      if (this.ms.Position + count > this.maxSize)
      {
        throw new NotSupportedException();
      }
      this.ms.Write(bytes, offset, count);
    }

    /// <inheritdoc/>
    public override void WriteByte(byte c)
    {
      if (this.ms.Position >= this.maxSize)
      {
        throw new NotSupportedException();
      }
      this.ms.WriteByte(c);
    }
  }
}